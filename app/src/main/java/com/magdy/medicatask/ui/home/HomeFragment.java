package com.magdy.medicatask.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.magdy.medicatask.MainActivity;
import com.magdy.medicatask.R;
import com.magdy.medicatask.adapters.RecyclerAdapterSpecialises;
import com.magdy.medicatask.adapters.RecyclerBranchesAdapter;

import com.magdy.medicatask.base.BaseFragment;
import com.magdy.medicatask.data.NetworkState;
import com.magdy.medicatask.data.models.BranchesModel;

import com.magdy.medicatask.databinding.FragmentHomeBinding;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment  extends BaseFragment {

    private HomeViewModel viewModel ;
    private FragmentHomeBinding binding ;
    private RecyclerBranchesAdapter adapter ;
     private List<BranchesModel.ItemBean.DataBean> list ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding =FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot() ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        adapter = new RecyclerBranchesAdapter();
        initView();
        observers();
        if (list == null)
        viewModel.getBranches();
    }

    private void initView (){
      binding.btnBranches.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              navigate(HomeFragmentDirections.actionHomeFragmentToBranchesFragment());

          }
      });


    }

   private void observers(){
        viewModel.branchesLiveData.observe(myActivity, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                if (networkState.getStatus() == NetworkState.Status.RUNNING){
                    MainActivity.startLoading();
                }else if (networkState.getStatus() == NetworkState.Status.SUCCESS){

                    MainActivity.stopLoading();
                    BranchesModel model = (BranchesModel) networkState.getData();
                    list = model.getItem().getData() ;
                    initRecycler();

                }else {
                    MainActivity.stopLoading();
                    Toast.makeText(myContext, networkState.getMassage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


   }
   private void initRecycler(){

        adapter.setList(list);
        binding.recyclerSpecialties.setAdapter(adapter);
        adapter.setOnItemClick(new RecyclerBranchesAdapter.OnItemClick() {
            @Override
            public void onClick(int branchId, int institutionId) {
                navigate(HomeFragmentDirections.actionHomeFragmentToFragmentSpecialises(branchId ,institutionId));
            }
        });


   }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null ;
    }
}
