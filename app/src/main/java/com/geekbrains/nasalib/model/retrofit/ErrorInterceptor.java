package com.geekbrains.nasalib.model.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ErrorInterceptor implements Interceptor {

    public static int code;

    @Override
    public Response intercept(Chain chain) throws IOException {
        // before request
        Request request = chain.request();
        // execute request
        Response response = chain.proceed(request);
        // after request
        // inspect status codes of unsuccessful responses
        code = response.code();
        return response;
    }
}
