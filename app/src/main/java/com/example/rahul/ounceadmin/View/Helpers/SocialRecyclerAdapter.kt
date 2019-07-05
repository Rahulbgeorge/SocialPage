package com.example.rahul.ounceadmin.View.Helpers

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rahul.ounceadmin.Model.Social
import com.example.rahul.ounceadmin.R
import com.example.rahul.ounceadmin.View.Helpers.SocialHelperHolders.BaseViewHolder
import com.example.rahul.ounceadmin.View.Helpers.SocialHelperHolders.ImageHolder
import com.example.rahul.ounceadmin.View.Helpers.SocialHelperHolders.ImageNTextHolder
import com.example.rahul.ounceadmin.View.Helpers.SocialHelperHolders.TextHolder

class SocialRecyclerAdapter(var userData:ArrayList<Social>,val mcontext: Context, val newsItemListener: NewsItemListener ):RecyclerView.Adapter<BaseViewHolder>(){

    fun addData( data:ArrayList<Social>)
    {
        var position=userData.size
        var currentSize=data.size
        for( i in 0..(currentSize-position-1))
        {
            userData.add(data.get(position))
        }
    }

    override fun getItemCount(): Int = userData.size

    override fun onBindViewHolder(itemHolder: BaseViewHolder, position: Int) {
        val socialData=userData[position]
        if(socialData==null)
            newsItemListener.callUpdateOldPost(userData.size)
        else {

            //GENERIC INITIALIZATIONS, INCLUDES BOTTOM VIEW AND HEADER LAYOUT
            itemHolder.likeButton.setOnClickListener {
                newsItemListener.callLikeTriggered(socialData,position)
                if(itemHolder.likeStatus) {itemHolder.likeStatus=false
                    socialData.likeCount=socialData.likeCount-1
                    itemHolder.likeCount.text=socialData.likeCount.toString()
                    itemHolder.likeImage.imageTintList=ContextCompat.getColorStateList(mcontext,R.color.inactive)
                } else {
                    itemHolder.likeStatus=true
                    socialData.likeCount+=1
                    itemHolder.likeCount.text=socialData.likeCount.toString()
                    itemHolder.likeImage.imageTintList=ContextCompat.getColorStateList(mcontext,R.color.active)
                }
            }
            itemHolder.shopButton.setOnClickListener({newsItemListener.callCommentTrigger(socialData,position)})
            itemHolder.likeCount.text=socialData.likeCount.toString()
            itemHolder.seenCount.text=socialData.seenCount.toString()
            itemHolder.jewelleryName.text=socialData.shopName
            itemHolder.jewelleryUser.text=socialData.username


            //VIEW SPECIFIC INITIALIZATIONSS
            when(socialData.holderType)
            {
                HolderType.BOTH->{
                    val imagetextHolder = itemHolder as ImageNTextHolder
                    imagetextHolder.ProductDescription.text = socialData.description.toString()
                    Glide.with(mcontext).load(socialData.imageUrl).into(itemHolder.mainImage)
                }

                HolderType.IMAGE_TYPE->{
                    val myHolder = itemHolder as ImageHolder
                    Glide.with(mcontext).load(socialData.imageUrl).into(itemHolder.mainImage)
                }

                HolderType.TEXT_TYPE->{
                    val Holdertext = itemHolder as TextHolder
                    Holdertext.ProductDescription.text = socialData.description.toString()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(userData.get(position)==null)
            return HolderType.REFRESH_HOLDER
        return userData.get(position).holderType
    }

    override fun onCreateViewHolder(p0: ViewGroup, type: Int): BaseViewHolder {
        val layoutInflater=LayoutInflater.from(p0.context)
        when(type)
        {
            HolderType.TEXT_TYPE -> return TextHolder(layoutInflater.inflate(R.layout.news_feed_text_social_media_item,p0,false) as View)
            HolderType.IMAGE_TYPE -> return ImageHolder(layoutInflater.inflate(R.layout.news_feed_social_media_item_temp,p0,false) as View)
            HolderType.BOTH -> return ImageNTextHolder(layoutInflater.inflate(R.layout.news_feed_text_image_social_media_item,p0,false) as View)
            HolderType.REFRESH_HOLDER -> return BaseViewHolder(layoutInflater.inflate(R.layout.news_feed_loading_item,p0,false) as View)
            else ->  return BaseViewHolder(layoutInflater.inflate(R.layout.news_feed_loading_item,p0,false) as View)
        }
    }



}