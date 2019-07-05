package com.example.rahul.ounceadmin.View.Helpers;

import android.util.Log;

import com.example.rahul.ounceadmin.Model.Social;
import com.example.rahul.ounceadmin.View.Helpers.NewsListeners.IClickListener;
import com.example.rahul.ounceadmin.View.Helpers.NewsListeners.IOldPostListener;

//button
public class NewsItemListener {

    IOldPostListener oldPostListener;
    IClickListener likeButtonListener;
    IClickListener commentButtonListener;
    IClickListener shopButtonListener;

    //------------------------- POST UPDATE LISTENER ------------------------------------------
    public void setOldPostListener(IOldPostListener opr)
    {
        oldPostListener=opr;
    }

    public void callUpdateOldPost(int size) { if(oldPostListener!=null) oldPostListener.updateOldPost(size); }


    //------------------------- LIKE BUTTON LISTENER -------------------------------------------
    public void setOnLikeClickListener(IClickListener obj){likeButtonListener=obj;}

    public void callLikeTriggered(final Social data, final int position) {

        if (likeButtonListener != null) {
            likeButtonListener.onClick(data, position);
            Log.e("calling","trigger");
        }
    }


    //-------------------------  COMMENT BUTTON LISTENER  -------------------------------------
    public void setOnCommentButtonListener(IClickListener obj){commentButtonListener=obj;}

    public void callCommentTrigger(final Social data, final int position){if(commentButtonListener!=null)
        new Thread(new Runnable() {
            @Override
            public void run() {
                commentButtonListener.onClick(data,position);
            }
        }).start();}

     //-------------------------- SHOP BUTTON LISTENER  -----------------------------------------
    public void setOnShopButtonListener(IClickListener obj){shopButtonListener=obj;}

    public void callShopTrigger(final Social data,final int position){
        if(shopButtonListener!=null)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    shopButtonListener.onClick(data,position);
                }
            }).start();
        }
    }

}
