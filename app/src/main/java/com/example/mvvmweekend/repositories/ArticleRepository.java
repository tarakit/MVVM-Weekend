package com.example.mvvmweekend.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmweekend.data.models.ArticleListResponse;
import com.example.mvvmweekend.data.models.ImageResponse;
import com.example.mvvmweekend.data.models.modelRequest.ArticleRequest;
import com.example.mvvmweekend.data.remote.ArticleService;
import com.example.mvvmweekend.data.remote.RetrofitInstance;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    public static final String TAG = ArticleRepository.class.getSimpleName();
    ArticleService service;

    public ArticleRepository(){
        service = RetrofitInstance.createService(ArticleService.class);
    }

    public MutableLiveData<Void> postArticle(ArticleRequest articleRequest){
        MutableLiveData<Void> liveData = new MutableLiveData<>();
        Call<Void> call = service.postArticle(articleRequest);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
        return liveData;
    }

    public MutableLiveData<ImageResponse> uploadImage(File file){
        Log.d(TAG, "uploadImage: "+ file);
        MutableLiveData<ImageResponse>  imageLiveData = new MutableLiveData<>();
        RequestBody request = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file",file.getAbsolutePath(),request);

        Call<ImageResponse> call = service.uploadImage(body);
        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                imageLiveData.setValue(response.body());
                Log.d(TAG, "onResponse: "+ response.body().getImage());
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
            }
        });

        return imageLiveData;
    }

    public MutableLiveData<ArticleListResponse> findAllArticles(int page, int limit){
        MutableLiveData<ArticleListResponse> articleLiveData = new MutableLiveData<>();
        Call<ArticleListResponse> call = service.getAllArticles(page, limit);
        call.enqueue(new Callback<ArticleListResponse>() {
            @Override
            public void onResponse(Call<ArticleListResponse> call, Response<ArticleListResponse> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+ response.body().getArticleList().toString());
                    articleLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArticleListResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.getMessage());
            }
        });
        return articleLiveData;
    }
}
