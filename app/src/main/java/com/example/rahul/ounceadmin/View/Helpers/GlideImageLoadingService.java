package com.example.rahul.ounceadmin.View.Helpers;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ss.com.bannerslider.ImageLoadingService;

public class GlideImageLoadingService implements ImageLoadingService {

    Context mcontext;
    public GlideImageLoadingService(Context mcontext)
    {
        this.mcontext=mcontext;
    }


    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(mcontext)
                .load(url)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadImage(int resource, ImageView imageView) {

    }

    @Override
    public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {

    }
}
