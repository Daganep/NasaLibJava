package com.geekbrains.nasalib.view.main;

import android.os.Bundle;

import com.geekbrains.nasalib.R;

import moxy.MvpAppCompatActivity;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void updateRecyclerView() {
        
    }
}