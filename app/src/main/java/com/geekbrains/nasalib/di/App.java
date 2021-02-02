package com.geekbrains.nasalib.di;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        appComponent = generateAppComponent();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    private AppComponent generateAppComponent(){
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}