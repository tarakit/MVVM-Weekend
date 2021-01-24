package com.example.mvvmweekend.data.models.modelRequest;

import com.google.gson.annotations.SerializedName;

public class ArticleRequest {

    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("image")
    String image_url;

    public ArticleRequest(String title, String description, String image_url) {
        this.title = title;
        this.description = description;
        this.image_url = image_url;
    }
}
