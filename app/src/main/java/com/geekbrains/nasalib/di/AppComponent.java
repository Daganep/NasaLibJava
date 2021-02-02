package com.geekbrains.nasalib.di;

import com.geekbrains.nasalib.presenter.CPPresenter;
import com.geekbrains.nasalib.presenter.MainPresenter;
import com.geekbrains.nasalib.view.main.MainRVA;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(MainPresenter mainPresenter);

    void inject(CPPresenter cpPresenter);
}
