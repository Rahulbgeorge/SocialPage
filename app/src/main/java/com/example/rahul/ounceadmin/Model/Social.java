package com.example.rahul.ounceadmin.Model;

public class Social {

    String id;
    String imageUrl;
    String imageBackColor;
    String iconUrl;
    String username;
    String shopName;
    int likeCount;
    int commentCount;
    int seenCount;


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


    public Social(String id,String imageUrl,String iconUrl,String username,String shopName,int likeCount,int commentCount,int seenCount)
    {
        this.id=id;
        this.imageUrl=imageUrl;
        this.iconUrl=iconUrl;
        this.username=username;
        this.shopName=shopName;
        this.likeCount=likeCount;
        this.seenCount=seenCount;
        this.commentCount=commentCount;

    }


}
