package com.example.mvvmweekend.data.remote;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicInterceptorHelper implements Interceptor {

    private final String credentials;

    public BasicInterceptorHelper(){
        this.credentials = Credentials.basic("AMSAPIADMIN", "AMSAPIP@SSWORD");
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Request request1 = request.newBuilder()
                .header("Authorization", credentials).build();
        return chain.proceed(request1);
    }
}
