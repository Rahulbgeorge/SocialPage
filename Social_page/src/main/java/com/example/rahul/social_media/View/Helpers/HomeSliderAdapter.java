package com.example.rahul.social_media.View.Helpers;

import com.example.rahul.social_media.Model.Advertisement;

import java.util.ArrayList;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class HomeSliderAdapter extends SliderAdapter {

    ArrayList<Advertisement> advertisements;
    public HomeSliderAdapter setImages(ArrayList<Advertisement> imageUrls)
    {
        this.advertisements=imageUrls;
        return this;
    }

    @Override
    public int getItemCount() {
        return advertisements.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        imageSlideViewHolder.bindImageSlide(this.advertisements.get(position).getImageUrl());

    }
}
