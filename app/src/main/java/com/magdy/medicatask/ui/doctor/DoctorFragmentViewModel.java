package com.magdy.medicatask.ui.doctor;

import androidx.lifecycle.MutableLiveData;

import com.magdy.medicatask.base.BaseViewModel;
import com.magdy.medicatask.data.NetworkState;
import com.magdy.medicatask.data.models.ModelDoctors;
import com.magdy.medicatask.network.ApiCalls;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


@HiltViewModel
public class DoctorFragmentViewModel extends BaseViewModel {


    MutableLiveData<NetworkState> doctorsLiveData = new MutableLiveData<>();
    private ApiCalls apiCalls ;
    @Inject
    public DoctorFragmentViewModel (ApiCalls apiCalls ){

        this.apiCalls = apiCalls ;
    }


     void getDoctors (int specialiseId , int branchId , int institutionId){

         doctorsLiveData.setValue(NetworkState.loading());
        apiCalls.getDoctors(branchId , specialiseId , institutionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelDoctors>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull ModelDoctors modelDoctors) {
                        if (modelDoctors!= null && modelDoctors.getItem()!= null && modelDoctors.getItem().getData() != null){
                            doctorsLiveData.setValue(NetworkState.loaded(modelDoctors));

                        }else {
                            doctorsLiveData.setValue(NetworkState.error(modelDoctors.getMessage()));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        doctorsLiveData.setValue(NetworkState.error(e));
                    }
                });




    }
}
