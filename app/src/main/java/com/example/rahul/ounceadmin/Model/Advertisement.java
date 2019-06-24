package com.example.rahul.ounceadmin.Model;

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
