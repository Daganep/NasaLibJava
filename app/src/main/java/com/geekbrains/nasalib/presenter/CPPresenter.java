package com.geekbrains.nasalib.presenter;


import android.util.Log;
import android.widget.ImageView;

import com.geekbrains.nasalib.di.App;
import com.geekbrains.nasalib.model.entity.CEInfo;
import com.geekbrains.nasalib.model.entity.Item;
import com.geekbrains.nasalib.model.entity.NasaResponse;
import com.geekbrains.nasalib.model.picasso.ImageSetter;
import com.geekbrains.nasalib.view.currentphoto.CPView;
import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class CPPresenter extends MvpPresenter<CPView> {

    @Inject
    NasaResponse nasaResponse;
    private final String TAG = "Retrofit";

    public CPPresenter(){
        App.getAppComponent().inject(this);
    }

    /*public CEInfo setInfo(Item item){
        Log.d(TAG, "res: " + nasaResponse);
        return new CEInfo(item);
    }*/
}
