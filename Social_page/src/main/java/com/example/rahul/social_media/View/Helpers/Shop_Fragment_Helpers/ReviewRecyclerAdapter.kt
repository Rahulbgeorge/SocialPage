package com.LMJ.ounceapp.Modules.News_feed.View.Helpers.Shop_Fragment_Helpers

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.rahul.social_media.Model.API.CompanyReview
import com.example.rahul.social_media.R
import com.example.rahul.social_media.View.Helpers.ShopHelperHolders.BaseViewHolder

class ReviewRecyclerAdapter(var review: java.util.ArrayList<CompanyReview>, val mContext: Context): RecyclerView.Adapter<BaseViewHolder>(){

    fun addData( data:ArrayList<CompanyReview>)
    {
        var position=review.size
        var currentSize=data.size
        for( i in 0 until currentSize-position)
        {
            review.add(data.get(position))
        }

    }
    override fun getItemCount(): Int = review.size


    override fun onBindViewHolder(itemHolder: BaseViewHolder, position: Int) {
        Log.e("Position Called", position.toString())
        val  reviewData = review[position]
        if(reviewData == null)

        else{
            itemHolder.review_likeButton.setOnClickListener {
                if (itemHolder.review_likeStatus) {
                    itemHolder.review_likeStatus = false
                    itemHolder.review_likeCount.text = reviewData.like_count.toString()
                    itemHolder.review_likeButton.imageTintList = ContextCompat.getColorStateList(mContext, R.color.inactive)
                } else{
                    itemHolder.review_likeStatus = true
                    itemHolder.review_likeCount.text = reviewData.like_count.toString()
                    itemHolder.review_likeButton.imageTintList = ContextCompat.getColorStateList(mContext,R.color.active);
                }
            }
            itemHolder.review_jewelleryUser.text = reviewData.username
            itemHolder.review.text = reviewData.comment
            itemHolder.review_likeCount.text = reviewData.like_count.toString()
        }
    }

    //Pending

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder {
        if(review.get(p1) == null){
            //If there are no comments for the store
            val itemView = LayoutInflater.from(p0.context).inflate(R.layout.shop_empty_review_item,p0,false) as View
            val vh = BaseViewHolder(itemView)
            return vh
        }
        else{
            //if there are reviews for the store
            val itemView = LayoutInflater.from(p0.context).inflate(R.layout.shop_review_item,p0,false) as View
            val vh = BaseViewHolder(itemView)
            return vh
        }
    }

}