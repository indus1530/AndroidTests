package com.example.isd.videoss;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.isd.videoss.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);
        bi.setCallback(this);


    }


}
