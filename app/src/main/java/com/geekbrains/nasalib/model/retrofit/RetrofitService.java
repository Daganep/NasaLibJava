package com.geekbrains.nasalib.model.retrofit;

import com.geekbrains.nasalib.model.entity.NasaResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("search")
    Observable<NasaResponse> getMedia(@Query("q") String q, @Query("page") String page);
}
