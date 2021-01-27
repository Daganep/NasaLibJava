package com.geekbrains.nasalib.model.retrofit;

import com.geekbrains.nasalib.model.entity.NasaResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("planetary/apod?api_key=Tv0CsKjtovbqSrb6ehgvLHdfhlpkV1KWJa69XI0O")
    Observable<NasaResponse> getMedia();
}
