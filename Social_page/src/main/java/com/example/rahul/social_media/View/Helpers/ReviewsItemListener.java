package com.example.rahul.social_media.View.Helpers;

import android.util.Log;

import com.example.rahul.social_media.Model.API.CompanyReview;
import com.example.rahul.social_media.View.Helpers.ReviewsListener.IClickListener;
import com.example.rahul.social_media.View.Helpers.ReviewsListener.IOldReviewListener;


public class ReviewsItemListener {

    IOldReviewListener oldReviewListener;
    IClickListener likeClickListener;

    public void setOldReviewListener(IOldReviewListener orl)
    {
        oldReviewListener = orl;
    }

    public void callUpdateOldReview(int size){
        if (oldReviewListener!=null) oldReviewListener.updateOldReview(size);
    }

    public void setOnLikeClickListener(IClickListener obj){likeClickListener = obj;}

    public void callLikeTriggered(final CompanyReview data, final int position){

        if(likeClickListener != null){
            likeClickListener.onClick(data, position);
            Log.e("Calling","Trigger");
        }
    }
}
