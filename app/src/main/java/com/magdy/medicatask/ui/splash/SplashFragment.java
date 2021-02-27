package com.magdy.medicatask.ui.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.magdy.medicatask.base.BaseFragment;
import com.magdy.medicatask.databinding.FragmentSplashBinding;
import com.magdy.medicatask.data.preference.MyPreference;

public class SplashFragment extends BaseFragment {

    private FragmentSplashBinding binding  ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentSplashBinding.inflate(getLayoutInflater());
        return binding.getRoot() ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (MyPreference.getUserToken().equals("")){

                    navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment());
                }else {
                    navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment());
                }

            }
        }, 3000);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null ;
    }
}
