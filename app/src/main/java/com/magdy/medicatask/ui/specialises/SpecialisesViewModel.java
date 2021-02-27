package com.magdy.medicatask.ui.specialises;

import androidx.lifecycle.MutableLiveData;

import com.magdy.medicatask.base.BaseFragment;
import com.magdy.medicatask.base.BaseViewModel;
import com.magdy.medicatask.data.NetworkState;
import com.magdy.medicatask.data.models.BranchesModel;
import com.magdy.medicatask.data.models.SpecialiseModel;
import com.magdy.medicatask.network.ApiCalls;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class SpecialisesViewModel extends BaseViewModel {

    MutableLiveData<NetworkState> specialisesLiveData = new MutableLiveData<>();


    private ApiCalls apiCalls ;

    @Inject
    public SpecialisesViewModel (ApiCalls apiCalls ){
        this.apiCalls = apiCalls;

    }

    void  getSpecialises (int idBranch , int idInstitution){

        specialisesLiveData.setValue(NetworkState.loading());
        apiCalls.getSpecialises(idBranch , idInstitution)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SpecialiseModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d) ;
                    }

                    @Override
                    public void onSuccess(@NonNull SpecialiseModel specialiseModel) {

                        if (specialiseModel!= null && specialiseModel.getItem()!= null && specialiseModel.getItem().getData() != null){
                            specialisesLiveData.setValue(NetworkState.loaded(specialiseModel));

                        }else {
                            specialisesLiveData.setValue(NetworkState.error(specialiseModel.getMessage()));
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        specialisesLiveData.setValue(NetworkState.error(e));
                    }
                });



    }
}
