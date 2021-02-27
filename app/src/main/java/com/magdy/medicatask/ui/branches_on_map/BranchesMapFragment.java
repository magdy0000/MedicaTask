package com.magdy.medicatask.ui.branches_on_map;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.magdy.medicatask.MainActivity;
import com.magdy.medicatask.data.NetworkState;
import com.magdy.medicatask.data.models.BranchesModel;
import com.magdy.medicatask.databinding.FragmentBranchesBinding;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.magdy.medicatask.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BranchesMapFragment extends BaseFragment implements OnMapReadyCallback {

  private FragmentBranchesBinding binding ;
  private BranchesViewModel viewModel ;
  private GoogleMap googleMap ;
  private List<Marker> markersList = new ArrayList<>();
  


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBranchesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.mapView.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(BranchesViewModel.class);
        binding.mapView.getMapAsync(this);
        getData();
    }

    private void getData (){
        viewModel.getBranches();
        viewModel.branchesLiveData.observe(myActivity, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                if (networkState.getStatus() == NetworkState.Status.RUNNING){
                    MainActivity.startLoading();
                }else if (networkState.getStatus() == NetworkState.Status.SUCCESS){

                    MainActivity.stopLoading();
                    BranchesModel model = (BranchesModel) networkState.getData();
                  
                    addMarkers(model.getItem().getData());
                }else {
                    Toast.makeText(myContext, networkState.getMassage(), Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    

    private void addMarkers(List<BranchesModel.ItemBean.DataBean> list){
        int size = list.size() ;
        for (int i = 0  ; i <size ; i++ ){

           markersList.add(googleMap.addMarker( new MarkerOptions()
                            .position(new LatLng(Double.parseDouble(list.get(i).getLat()) , Double.parseDouble(list.get(i).getLng()) ))
                            .title(list.get(i).getTitle())));


        }
         moveCamera(markersList);

    }

    private void moveCamera(List<Marker> markers){

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 200);
        googleMap.animateCamera(cu);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap ;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.mapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.mapView.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.mapView.onStart();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.mapView.onLowMemory();
    }
}
