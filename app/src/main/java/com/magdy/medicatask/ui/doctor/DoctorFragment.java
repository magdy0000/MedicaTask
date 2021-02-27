package com.magdy.medicatask.ui.doctor;

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
import com.magdy.medicatask.adapters.RecyclerDoctorAdapter;
import com.magdy.medicatask.base.BaseFragment;
import com.magdy.medicatask.base.BaseViewModel;
import com.magdy.medicatask.data.NetworkState;
import com.magdy.medicatask.data.models.ModelDoctors;
import com.magdy.medicatask.data.models.SpecialiseModel;
import com.magdy.medicatask.databinding.FragmentDoctorBinding;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DoctorFragment extends BaseFragment {

    private FragmentDoctorBinding binding ;
    private DoctorFragmentViewModel viewModel ;
    private RecyclerDoctorAdapter adapter ;
    private int branchId ;
    private  int institutionId ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentDoctorBinding.inflate(getLayoutInflater());


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DoctorFragmentViewModel.class);
        adapter=  new RecyclerDoctorAdapter() ;
        int specialiseId = DoctorFragmentArgs.fromBundle(getArguments()).getSpecialiseId();
        institutionId = DoctorFragmentArgs.fromBundle(getArguments()).getInstitutionId();
         branchId = DoctorFragmentArgs.fromBundle(getArguments()).getBranchId();
        viewModel.getDoctors(specialiseId , branchId ,institutionId);
        observer();
    }


    private void observer (){
        viewModel.doctorsLiveData.observe(myActivity, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                if (networkState.getStatus() == NetworkState.Status.RUNNING){
                    MainActivity.startLoading();
                }else if (networkState.getStatus() == NetworkState.Status.SUCCESS){

                    MainActivity.stopLoading();
                    ModelDoctors model = (ModelDoctors) networkState.getData();
                    initRecycler(model.getItem().getData());

                }else {
                    Toast.makeText(myContext, networkState.getMassage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void initRecycler(List<ModelDoctors.ItemBean.DataBean> list ){

        adapter.setList(list);
        binding.recyclerDoctors.setAdapter(adapter);
        adapter.setOnItemClick(new RecyclerDoctorAdapter.OnItemClick() {
            @Override
            public void onClick(int doctorId , String doctorName) {
               navigate(DoctorFragmentDirections.actionDoctorFragmentToReservationFragment(branchId , doctorName , doctorId , institutionId));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding =  null ;
    }
}
