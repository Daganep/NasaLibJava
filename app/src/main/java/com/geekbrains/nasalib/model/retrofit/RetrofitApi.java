package com.geekbrains.nasalib.model.retrofit;

import com.geekbrains.nasalib.model.entity.NasaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private final String baseUrl = "https://images-api.nasa.gov/";
    private final String page = "1";
    private final String searchKey = "hubble";
    private RetrofitService api;
    private final String TAG = "Retrofit";

    public Observable<NasaResponse> requestServer(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
        api = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(RetrofitService.class);
        return api.getMedia().subscribeOn(Schedulers.io());
    }
}
