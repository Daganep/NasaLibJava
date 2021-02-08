package com.geekbrains.nasalib.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.geekbrains.nasalib.model.database.AppDatabase;
import com.geekbrains.nasalib.utils.picasso.ImageSetter;
import com.geekbrains.nasalib.model.retrofit.RetrofitApi;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "nasalib_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    RetrofitApi provideRetrofitApi(){return new RetrofitApi();}

    @Singleton
    @Provides
    ImageSetter provideImageSetter(){return new ImageSetter();}

    @Singleton
    @Provides
    Context provideContext() {
        return application;
    }
}
