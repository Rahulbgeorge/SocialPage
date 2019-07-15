package com.example.rahul.social_media.Model;

import android.util.Log;

import com.example.rahul.social_media.View.Activities.ShopPage;
import com.example.rahul.social_media.View.Helpers.HolderType;

public class Social {

    public String id,imageUrl,imageBackColor,iconUrl,username,description,shopName;
    public int ShopId;
    public int likeCount,seenCount,commentCount;
    public int holderType=HolderType.IMAGE_TYPE;


    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageBackColor() {
        return imageBackColor;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getShopName() {
        return shopName;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getSeenCount() {
        return seenCount;
    }


    public Social(String id,String imageUrl,String iconUrl,String username,String shopName,int likeCount,int commentCount,int seenCount,String description,String shopId)
    {
        this.id=id;
        this.imageUrl=imageUrl;
        this.iconUrl=iconUrl;
        this.username=username;
        this.shopName=shopName;
        this.likeCount=likeCount;
        this.seenCount=seenCount;
        this.commentCount=commentCount;
        this.description=description;
        this.ShopId=Integer.parseInt(shopId);
        Log.e("saved value",ShopId+"");
        createHolderType();
    }

    public void createHolderType()
    {
        Log.e("social","description"+description);
        if(description.equals("null"))
        {
            holderType=HolderType.IMAGE_TYPE;
        }
        else
        {
            holderType=HolderType.BOTH;
        }
    }


}
