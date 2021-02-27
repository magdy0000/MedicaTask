package com.magdy.medicatask.ui.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.magdy.medicatask.MainActivity;
import com.magdy.medicatask.R;
import com.magdy.medicatask.base.BaseFragment;
import com.magdy.medicatask.databinding.FragmentLoginBinding;
import com.magdy.medicatask.data.models.LoginModel;
import com.magdy.medicatask.data.preference.MyPreference;
import com.magdy.medicatask.data.NetworkState;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends BaseFragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        onClick();
        observer();

    }
    public void observer(){

        loginViewModel.loginLiveData.observe(myActivity, new Observer<NetworkState>() {
            @Override
            public void onChanged(NetworkState networkState) {
                if (networkState.getStatus() == NetworkState.Status.SUCCESS){
                    MainActivity.stopLoading();
                    LoginModel model = ((LoginModel) networkState.getData());
                    MyPreference.setUserToken(model.getItem().getAuthorization());
                    navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment());
                }else {
                    MainActivity.stopLoading();
                    Toast.makeText(myContext, networkState.getMassage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onClick() {

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation(binding.phoneEditText.getText().toString().trim()
                        , binding.passwordEditText.getText().toString().trim());
            }
        });

    }

    public void validation(String phone, String password) {
        if (phone.isEmpty()) {
            binding.phoneEditText.setError(getString(R.string.required));
        } else if (password.isEmpty()) {
            binding.passwordEditText.setError(getString(R.string.required));
        } else if (phone.length() < 11 ) {

            Toast.makeText(myContext, "Wrong phone number", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(myContext, "Password is should be 6 characters", Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.startLoading();
            loginViewModel.login(phone, password);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}