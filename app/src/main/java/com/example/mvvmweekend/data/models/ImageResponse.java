package com.example.mvvmweekend.data.models;

import com.google.gson.annotations.SerializedName;

public class ImageResponse {

    @SerializedName("data")
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
