package com.example.mvvmweekend.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmweekend.data.models.ArticleListResponse;
import com.example.mvvmweekend.data.remote.ArticleService;
import com.example.mvvmweekend.data.remote.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository {
    public static final String TAG = ArticleRepository.class.getSimpleName();
    ArticleService service;

    public ArticleRepository(){
        service = RetrofitInstance.createService(ArticleService.class);
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
