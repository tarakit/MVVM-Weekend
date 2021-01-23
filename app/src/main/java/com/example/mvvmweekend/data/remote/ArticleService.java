package com.example.mvvmweekend.data.remote;

import com.example.mvvmweekend.data.models.ArticleListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ArticleService {

    @GET("/v1/api/articles")
    Call<ArticleListResponse> getAllArticles(@Query("page") int page, @Query("limit") int limit);
}
