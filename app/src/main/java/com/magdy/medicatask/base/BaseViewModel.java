package com.magdy.medicatask.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {


    protected CompositeDisposable disposable ;

   public  BaseViewModel (){

       disposable=  new CompositeDisposable();
   }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
