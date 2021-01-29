package com.geekbrains.nasalib.di;

import com.geekbrains.nasalib.presenter.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainPresenter mainPresenter);
}
