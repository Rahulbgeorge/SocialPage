package com.example.rahul.social_media.Model;

public class Advertisement {
    //advertisement data
    String imageUrl,title,message;

    public Advertisement(String imageUrl)
    {
        this.imageUrl=imageUrl;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

}
