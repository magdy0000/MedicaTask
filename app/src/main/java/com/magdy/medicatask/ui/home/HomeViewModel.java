package com.magdy.medicatask.ui.home;

import androidx.lifecycle.MutableLiveData;

import com.magdy.medicatask.base.BaseViewModel;
import com.magdy.medicatask.data.NetworkState;
import com.magdy.medicatask.data.models.BranchesModel;
import com.magdy.medicatask.data.models.ModelDoctors;
import com.magdy.medicatask.data.models.SpecialiseModel;
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
public class HomeViewModel  extends BaseViewModel {

    MutableLiveData<NetworkState> branchesLiveData  = new MutableLiveData<>() ;
    private ApiCalls apiCalls ;

    @Inject
    public HomeViewModel (ApiCalls apiCalls ){
        this.apiCalls = apiCalls;

    }
     void getBranches() {
        branchesLiveData.setValue(NetworkState.loading());
        apiCalls.getBranches("30.0609", "31.219698", 3, 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<BranchesModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d) ;
                    }

                    @Override
                    public void onSuccess(@NonNull BranchesModel branchesModel) {

                        if (branchesModel!= null && branchesModel.getItem()!= null && branchesModel.getItem().getData() != null){
                            branchesLiveData.setValue(NetworkState.loaded(branchesModel));

                        }else {
                            branchesLiveData.setValue(NetworkState.error(branchesModel.getMessage()));
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        branchesLiveData.setValue(NetworkState.error(e));
                    }
                });


    }

//    public void getData(){
//        homeLiveData.setValue(NetworkState.loading());
//        apiCalls.getSpecialises(1277, 1096)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<SpecialiseModel>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        disposable.add(d) ;
//                    }
//
//                    @Override
//                    public void onSuccess(@NonNull SpecialiseModel specialiseModel) {
//                        if (specialiseModel!= null && specialiseModel.getItem()!= null && specialiseModel.getItem().getData() != null){
//                            homeLiveData.setValue(NetworkState.loaded(specialiseModel));
//
//                        }else {
//                            homeLiveData.setValue(NetworkState.error(specialiseModel.getMessage()));
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        homeLiveData.setValue(NetworkState.error(e));
//                    }
//                });
//
//
//
//    }


}
