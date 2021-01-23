package com.example.mvvmweekend.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static final String BASE_URL = "http://110.74.194.124:15011";

    private static HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC);

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(150, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(new BasicInterceptorHelper());

    private static Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }

}
