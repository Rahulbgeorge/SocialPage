package com.example.rahul.social_media.View.Helpers.Shop_Fragment_Helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.rahul.social_media.Model.API.CompanyPostOverview;
import com.example.rahul.social_media.R;


import java.util.List;

public class Shop_Gallery_Adapter extends RecyclerView.Adapter<Shop_Gallery_Adapter.MyViewHolder>  {

    List<CompanyPostOverview> companyPostOverviewList;
    Context context;

    public Shop_Gallery_Adapter(Context context, List<CompanyPostOverview> companyPostOverviews) {
        this.companyPostOverviewList=companyPostOverviews;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_gallery_item,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(context)
                .load(companyPostOverviewList.get(position).getImageUrl())
                .into(holder.image);
//        holder.image.setImageResource(companyPostOverviewList.get(position).getImageUrl());

    }

    @Override
    public int getItemCount() {
        return companyPostOverviewList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            image = (ImageView) itemView.findViewById(R.id.images);
        }
    }
}
