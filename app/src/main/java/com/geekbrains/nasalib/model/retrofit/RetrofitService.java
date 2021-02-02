package com.geekbrains.nasalib.model.retrofit;

import com.geekbrains.nasalib.model.entity.NasaResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("search?q=hubble&page=1")
    Observable<NasaResponse> getMedia();
}
