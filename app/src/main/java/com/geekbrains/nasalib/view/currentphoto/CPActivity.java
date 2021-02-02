package com.geekbrains.nasalib.view.currentphoto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.geekbrains.nasalib.R;

public class CPActivity extends AppCompatActivity implements CPView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_p);
    }
}