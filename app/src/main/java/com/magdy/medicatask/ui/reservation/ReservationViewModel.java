package com.magdy.medicatask.ui.reservation;

import androidx.lifecycle.MutableLiveData;

import com.magdy.medicatask.base.BaseViewModel;
import com.magdy.medicatask.data.NetworkState;
import com.magdy.medicatask.data.models.ModelAppointments;
import com.magdy.medicatask.data.models.ReservationModel;
import com.magdy.medicatask.data.models.SpecialiseModel;
import com.magdy.medicatask.data.models.TimeModel;
import com.magdy.medicatask.network.ApiCalls;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;

@HiltViewModel
public class ReservationViewModel extends BaseViewModel {

    MutableLiveData<NetworkState> reservationLiveData = new MutableLiveData<>() ;
    MutableLiveData<NetworkState> appointmentsLiveData = new MutableLiveData<>() ;
    MutableLiveData<NetworkState> timeLiveData =  new MutableLiveData<>();
    private ApiCalls apiCalls ;
    @Inject
    public ReservationViewModel (ApiCalls apiCalls ){
       this.apiCalls =  apiCalls ;

    }


    void getAppointments (int branchId, int doctorId ){
        appointmentsLiveData.setValue(NetworkState.loading());
        apiCalls.getAppointment(branchId , doctorId , "normal")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelAppointments>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull ModelAppointments appointments) {
                        if (appointments!= null && appointments.getItem()!= null && appointments.getItem().getData() != null){
                            appointmentsLiveData.setValue(NetworkState.loaded(appointments));

                        }else {
                            appointmentsLiveData.setValue(NetworkState.error(appointments.getMessage()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        appointmentsLiveData.setValue(NetworkState.error(e));
                    }
                });

    }

    void getTime (int branchId, int doctorId , int dayNumber , int institutionId ,  String date){
        timeLiveData.setValue(NetworkState.loading());

        apiCalls.getTime(branchId , doctorId , "normal" ,institutionId , dayNumber ,date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TimeModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull TimeModel timeModel) {
                        if (timeModel!= null && timeModel.getItem()!= null && timeModel.getItem().getData() != null){
                            timeLiveData.setValue(NetworkState.loaded(timeModel));

                        }else {
                            timeLiveData.setValue(NetworkState.error(timeModel.getMessage()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        timeLiveData.setValue(NetworkState.error(e));
                    }
                });

    }

    void makeReservation(int branchId , int institutionId ,String date,int doctorId,String time ){
        reservationLiveData.setValue(NetworkState.loading());
        apiCalls.makeReservation(branchId,institutionId,date,doctorId,time,"normal_reservation" )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ReservationModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull ReservationModel reservationModel) {
                        if (reservationModel!= null && reservationModel.getMessage()!= null ){
                            reservationLiveData.setValue(NetworkState.loaded(reservationModel));

                        }else {
                            reservationLiveData.setValue(NetworkState.error(reservationModel.getMessage()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        reservationLiveData.setValue(NetworkState.error(e));
                    }
                });


    }



}
