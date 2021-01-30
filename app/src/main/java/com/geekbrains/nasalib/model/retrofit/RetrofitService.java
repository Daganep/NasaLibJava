package com.geekbrains.nasalib.model.retrofit;

import com.geekbrains.nasalib.model.entity.NasaResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("api")
    Single<NasaResponse> getMedia(@Query("q") String searchKey,
                                     @Query("page") String page);
}
