package com.geekbrains.nasalib.model.retrofit;

import com.geekbrains.nasalib.model.entity.NasaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {

    private final String baseUrl = "https://images-api.nasa.gov/search";
    private final String page = "1";
    private final String searchKey = "apollo+11";
    private RetrofitService api;

    public RetrofitApi(){
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
    }

    public Single<NasaResponse> requestServer(String searchKey, String page) {
        return api.getMedia(searchKey, page)
                .subscribeOn(Schedulers.io());
    }
}
