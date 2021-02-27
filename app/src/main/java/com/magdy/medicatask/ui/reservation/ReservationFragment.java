package com.magdy.medicatask.ui.reservation;

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
import com.magdy.medicatask.R;
import com.magdy.medicatask.adapters.RecyclerAdapterDays;
import com.magdy.medicatask.adapters.RecyclerAdapterTime;
import com.magdy.medicatask.adapters.RecyclerDatesAdapter;
import com.magdy.medicatask.base.BaseFragment;
import com.magdy.medicatask.data.NetworkState;
import com.magdy.medicatask.data.models.BranchesModel;
import com.magdy.medicatask.data.models.ModelAppointments;
import com.magdy.medicatask.data.models.ReservationModel;
import com.magdy.medicatask.data.models.TimeModel;
import com.magdy.medicatask.databinding.FragmentReservationBinding;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReservationFragment extends BaseFragment {


    private RecyclerAdapterDays adapter ;
    private RecyclerDatesAdapter adapterDates;
    private RecyclerAdapterTime adapterTime ;
    private FragmentReservationBinding binding  ;
    private ReservationViewModel viewModel ;
    private String doctorName;
    private int  institutionId ;
    private int doctorId ;
    private int branchId ;
    private String mDate = "" ;
    private String mTime = "" ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReservationBinding.inflate(getLayoutInflater()) ;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ReservationViewModel.class);
        adapterDates = new RecyclerDatesAdapter() ;
        adapter = new RecyclerAdapterDays() ;
        adapterTime = new RecyclerAdapterTime() ;
        doctorName = ReservationFragmentArgs.fromBundle(getArguments()).getDoctorName();
        institutionId = ReservationFragmentArgs.fromBundle(getArguments()).getInstitutionId();
        doctorId  = ReservationFragmentArgs.fromBundle(getArguments()).getDoctorId();
        branchId = ReservationFragmentArgs.fromBundle(getArguments()).getBranchId();
        viewModel.getAppointments(branchId, doctorId);
        observers();
        onDateSelected();
        onClickReservation();
    }

    private void observers(){
        viewModel.appointmentsLiveData.observe(myActivity, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                if (networkState.getStatus() == NetworkState.Status.RUNNING){
                    MainActivity.startLoading();
                }else if (networkState.getStatus() == NetworkState.Status.SUCCESS){

                    MainActivity.stopLoading();
                    ModelAppointments model = (ModelAppointments) networkState.getData();
                    ModelAppointments.ItemBean.DataBean data = model.getItem().getData().get(0) ;
                    initView(data.getPrice() ,data.getTime_from() , data.getTime_to() );
                    initRecyclerView(model.getItem().getData());
                }else {
                    MainActivity.stopLoading();
                    Toast.makeText(myContext, networkState.getMassage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.timeLiveData.observe(myActivity, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                if (networkState.getStatus() == NetworkState.Status.RUNNING){
                    MainActivity.startLoading();
                }else if (networkState.getStatus() == NetworkState.Status.SUCCESS){

                    MainActivity.stopLoading();
                    TimeModel model = (TimeModel) networkState.getData();
                    initRecyclerTime(model.getItem().getData());

                }else {
                    MainActivity.stopLoading();
                    Toast.makeText(myContext, networkState.getMassage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.reservationLiveData.observe(myActivity, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                if (networkState.getStatus() == NetworkState.Status.RUNNING){
                    MainActivity.startLoading();
                }else if (networkState.getStatus() == NetworkState.Status.SUCCESS){

                    MainActivity.stopLoading();
                    ReservationModel model = (ReservationModel) networkState.getData();
                    Toast.makeText(myContext, model.getMessage(), Toast.LENGTH_SHORT).show();
                    if (model.getCode() == 2000){
                       navigate(ReservationFragmentDirections.actionReservationFragmentToLoginFragment());
                    }else if (model.getCode() == 100){

                        navigate(ReservationFragmentDirections.actionReservationFragmentToHomeFragment());
                    }



                }else {
                    MainActivity.stopLoading();
                    Toast.makeText(myContext, networkState.getMassage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private  void  initView (String price , String from , String to){
        binding.textDoctorName.setText(doctorName);
        binding.textPrice.setText(price);
        binding.textFrom.setText(from);
        binding.textTo.setText(to);

    }

    private void initRecyclerView (List<ModelAppointments.ItemBean.DataBean> list){
        adapter.setList(list);
        binding.recyclerDate.setAdapter(adapter);

    }
    private void initRecyclerTime(List<TimeModel.ItemBean.DataBean> list){

        adapterTime.setList(list);
        binding.timeHeader.setVisibility(View.VISIBLE);
        binding.recyclerTime.setAdapter(adapterTime);
        adapterTime.setOnTimeClick(new RecyclerAdapterTime.OnTimeClick() {
            @Override
            public void onClick(String time) {
               mTime  = time;
            }
        });

    }
    private void onDateSelected(){
        adapterDates.setOnDateClick(new RecyclerDatesAdapter.OnDateClick() {
            @Override
            public void onClick(String date, int dayNumber) {
                mDate = date ;
                mTime = "" ;
                viewModel.getTime(branchId,doctorId,dayNumber,institutionId,date);

            }
        });
    }
    private void onClickReservation(){
        binding.btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              validationReservation();
            }
        });
    }
    private void validationReservation(){


        if (mDate.isEmpty()){
            Toast.makeText(myContext, getString(R.string.choose_date), Toast.LENGTH_SHORT).show();
        }else if (mTime.isEmpty()){
            Toast.makeText(myContext, getString(R.string.choose_time), Toast.LENGTH_SHORT).show();
        }else {

            viewModel.makeReservation(branchId,institutionId,mDate , doctorId ,mTime);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null ;
    }
}
