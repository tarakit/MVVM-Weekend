package com.example.mvvmweekend.view.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmweekend.data.models.ArticleListResponse;
import com.example.mvvmweekend.repositories.ArticleRepository;

public class HomeViewModel extends ViewModel {
    ArticleRepository articleRepository;

    public void init(){
        articleRepository = new ArticleRepository();
    }

    public MutableLiveData<ArticleListResponse> findAllArticles(int page, int limit){
        return articleRepository.findAllArticles(page,limit);
    }

}
