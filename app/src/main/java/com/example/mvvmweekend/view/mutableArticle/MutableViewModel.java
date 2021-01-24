package com.example.mvvmweekend.view.mutableArticle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmweekend.data.models.ImageResponse;
import com.example.mvvmweekend.data.models.modelRequest.ArticleRequest;
import com.example.mvvmweekend.repositories.ArticleRepository;

import java.io.File;

public class MutableViewModel extends ViewModel {

    ArticleRepository articleRepository;

    public void init(){
        articleRepository = new ArticleRepository();
    }

    public MutableLiveData<ImageResponse> uploadImage(File file){
        return articleRepository.uploadImage(file);
    }

    public MutableLiveData<Void> postArticle(ArticleRequest articleRequest){
        return articleRepository.postArticle(articleRequest);
    }
}
