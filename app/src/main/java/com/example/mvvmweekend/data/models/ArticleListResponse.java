package com.example.mvvmweekend.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleListResponse {

    @SerializedName("data")
    List<Article> articleList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
