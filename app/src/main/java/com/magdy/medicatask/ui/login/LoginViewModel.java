package com.magdy.medicatask.ui.login;

import androidx.lifecycle.MutableLiveData;

import com.magdy.medicatask.base.BaseViewModel;
import com.magdy.medicatask.data.models.LoginModel;
import com.magdy.medicatask.network.ApiCalls;
import com.magdy.medicatask.data.NetworkState;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class LoginViewModel extends BaseViewModel {

    MutableLiveData<NetworkState> loginLiveData = new MutableLiveData<>();
    private ApiCalls apiCalls  ;

    @Inject
    public LoginViewModel (ApiCalls apiCalls ){

      this.apiCalls = apiCalls ;

    }


   public void login(String phone ,String password){

        apiCalls.login(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoginModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull LoginModel loginModel) {
                        if (loginModel.getItem() != null) {
                            loginLiveData.setValue(NetworkState.loaded(loginModel));
                        }else {
                            loginLiveData.setValue(NetworkState.error(loginModel.getMessage()));
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loginLiveData.setValue(NetworkState.error(e));
                    }
                });
   }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}