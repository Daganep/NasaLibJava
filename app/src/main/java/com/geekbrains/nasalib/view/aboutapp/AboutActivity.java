package com.geekbrains.nasalib.view.aboutapp;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.geekbrains.nasalib.R;
import com.geekbrains.nasalib.databinding.ActivityAboutBinding;

import moxy.MvpAppCompatActivity;

public class AboutActivity extends MvpAppCompatActivity {

    private ActivityAboutBinding aboutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_about);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(aboutBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override public boolean onSupportNavigateUp () {
        onBackPressed() ;
        return true;
    }
}