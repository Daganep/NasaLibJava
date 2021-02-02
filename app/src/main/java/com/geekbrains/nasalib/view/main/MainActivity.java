package com.geekbrains.nasalib.view.main;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import com.geekbrains.nasalib.R;
import com.geekbrains.nasalib.databinding.ActivityMainBinding;
import com.geekbrains.nasalib.presenter.main.MainPresenter;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private ActivityMainBinding mainBinding;
    private final String TAG = "Retrofit";
    @InjectPresenter
    MainPresenter mainPresenter;

    private MainRVA mainRVA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        mainPresenter.requestFromServer();
    }

    private void initRecycler(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mainBinding.recyclerView.setLayoutManager(gridLayoutManager);
        mainRVA = new MainRVA(mainPresenter.getMedia(), mainPresenter);
        mainBinding.recyclerView.setAdapter(mainRVA);
    }

    @Override
    public void updateRecyclerView(){
        initRecycler();
        mainRVA.notifyDataSetChanged();
    }
}