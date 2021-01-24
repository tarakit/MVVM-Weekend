package com.example.mvvmweekend.data.remote;

import com.example.mvvmweekend.data.models.Article;
import com.example.mvvmweekend.data.models.ArticleListResponse;
import com.example.mvvmweekend.data.models.ImageResponse;
import com.example.mvvmweekend.data.models.modelRequest.ArticleRequest;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ArticleService {

    @GET("/v1/api/articles")
    Call<ArticleListResponse> getAllArticles(@Query("page") int page, @Query("limit") int limit);

    @Multipart
    @POST("/v1/api/uploadfile/single")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part image);

    @POST("/v1/api/articles")
    Call<Void> postArticle(@Body ArticleRequest articleRequest);
}
