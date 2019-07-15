package com.example.rahul.social_media.Model.API;

import com.google.gson.annotations.SerializedName;

public class CompanyReview {

    public String comment;
    @SerializedName("user")
    String username;
    public float rating;
    public int like_count;

    public CompanyReview(String comment,String username,float rating)
    {}

    public String getComment(){return comment;}
    public String getUsername(){return username;}
    public float getRating(){return rating;}
}
