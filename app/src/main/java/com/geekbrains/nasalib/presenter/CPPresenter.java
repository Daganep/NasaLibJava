package com.geekbrains.nasalib.presenter;

import com.geekbrains.nasalib.di.App;
import com.geekbrains.nasalib.view.currentphoto.CPView;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class CPPresenter extends MvpPresenter<CPView> {

    public CPPresenter(){
        App.getAppComponent().inject(this);
    }
}
