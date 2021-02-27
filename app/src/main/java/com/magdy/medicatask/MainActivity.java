package com.magdy.medicatask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.magdy.medicatask.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {



    private static ActivityMainBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      binding = ActivityMainBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());


    }

    public static void startLoading(){
       binding.load.start();
        binding.load.setVisibility(View.VISIBLE);

    }
    public static void stopLoading(){

        binding.load.setVisibility(View.GONE);
        binding.load.stop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null ;
    }
}