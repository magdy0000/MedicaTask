package com.magdy.medicatask.ui.specialises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.magdy.medicatask.MainActivity;
import com.magdy.medicatask.adapters.RecyclerAdapterSpecialises;
import com.magdy.medicatask.base.BaseFragment;
import com.magdy.medicatask.data.NetworkState;
import com.magdy.medicatask.data.models.ModelDoctors;
import com.magdy.medicatask.data.models.SpecialiseModel;
import com.magdy.medicatask.databinding.FragmentSpecialisesBinding;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentSpecialises extends BaseFragment {

    private FragmentSpecialisesBinding binding;
    private SpecialisesViewModel viewModel;
    private RecyclerAdapterSpecialises adapter ;
    private int branchId;
    private int institutionId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSpecialisesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SpecialisesViewModel.class);
        adapter = new RecyclerAdapterSpecialises();
        branchId = FragmentSpecialisesArgs.fromBundle(getArguments()).getBranchId();
        institutionId = FragmentSpecialisesArgs.fromBundle(getArguments()).getIdInstitution();
        viewModel.getSpecialises(branchId, institutionId);
        observers();
    }


    private void observers() {
        viewModel.specialisesLiveData.observe(myActivity, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                if (networkState.getStatus() == NetworkState.Status.RUNNING) {
                    MainActivity.startLoading();

                } else if (networkState.getStatus() == NetworkState.Status.SUCCESS) {

                    MainActivity.stopLoading();
                    SpecialiseModel model = (SpecialiseModel) networkState.getData();
                    initRecycler(model.getItem().getData());

                } else {
                    Toast.makeText(myContext, networkState.getMassage(), Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

        private void initRecycler (List < SpecialiseModel.ItemBean.DataBean > list) {

            adapter.setList(list);
            binding.recyclerSpecialties.setAdapter(adapter);
            adapter.setOnItemClick(new RecyclerAdapterSpecialises.OnItemClick() {
                @Override
                public void onClick(int specialiseId) {

                    navigate(FragmentSpecialisesDirections.actionFragmentSpecialisesToDoctorFragment(specialiseId, branchId , institutionId));
                }
            });
        }


        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }
    }
