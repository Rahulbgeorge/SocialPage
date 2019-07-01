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
import com.example.rahul.ounceadmin.View.Helpers.SocialHelperHolders.ImageHolder
import com.example.rahul.ounceadmin.View.Helpers.SocialHelperHolders.ImageNTextHolder
import com.example.rahul.ounceadmin.View.Helpers.SocialHelperHolders.TextHolder

class SocialRecyclerAdapter(var userData:ArrayList<Social>,val mcontext: Context, val newsItemListener: NewsItemListener ):RecyclerView.Adapter<SocialRecyclerAdapter.BaseViewHolder>(){

    public fun addData( data:ArrayList<Social>)
    {
        var position=userData.size
        var currentSize=data.size
        for( i in 0..(currentSize-position-1))
        {
            userData.add(data.get(position))
        }

    }

    override fun getItemCount(): Int {
        return userData.size
    }
    
    override fun onBindViewHolder(itemHolder: BaseViewHolder, position: Int) {
        Log.e("Postion called",position.toString())

//        if(position==userData.size-2)
//        {
//            Log.e("Custom","position"+position)
//            oldRecyclerLister.callUpdateOldPost(userData.size)
//        }
        val socialData=userData[position]

        if(socialData==null)
        {
            newsItemListener.callUpdateOldPost(userData.size)
        }
        else {

            //generic initializations
            itemHolder.likeButton.setOnClickListener {
                newsItemListener.callLikeTriggered(socialData,position)
                if(itemHolder.likeStatus) {itemHolder.likeStatus=false
                    itemHolder.likeImage.imageTintList=ContextCompat.getColorStateList(mcontext,R.color.inactive)
                } else {
                    itemHolder.likeStatus=true
                    itemHolder.likeImage.imageTintList=ContextCompat.getColorStateList(mcontext,R.color.active)
                }
            }
            itemHolder.commentButton.setOnClickListener({newsItemListener.callCommentTrigger(socialData,position)})
            itemHolder.likeCount.text=socialData.likeCount.toString()
            itemHolder.commentCount.text = socialData.commentCount.toString()
            itemHolder.seenCount.text=socialData.seenCount.toString()

            if (socialData.holderType == HolderType.BOTH) {
                val imagetextHolder = itemHolder as ImageNTextHolder
                imagetextHolder.jewellerName.text = socialData.shopName
                imagetextHolder.username.text = socialData.username
                imagetextHolder.ProductDescription.text = socialData.description.toString()
                Glide.with(mcontext).load(socialData.imageUrl).into(itemHolder.mainImage)

            } else if (socialData.holderType == HolderType.IMAGE_TYPE) {
                val myHolder = itemHolder as ImageHolder

                myHolder.jewellerName.text = socialData.shopName
                myHolder.username.text = socialData.username
                Glide.with(mcontext).load(socialData.imageUrl).into(itemHolder.mainImage)
            } else if (socialData.holderType == HolderType.TEXT_TYPE) {
                val Holdertext = itemHolder as TextHolder

                Holdertext.jewellerName.text = socialData.shopName
                Holdertext.username.text = socialData.username
                Holdertext.ProductDescription.text = socialData.description.toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(userData.get(position)==null)
            return HolderType.REFRESH_HOLDER
        return userData.get(position).holderType
    }

    override fun onCreateViewHolder(p0: ViewGroup, type: Int): BaseViewHolder {

        if(type==HolderType.BOTH){
            //Setting the News Feed for both image and text Layout
            Log.e("Type",""+type)
            val itemView=LayoutInflater.from(p0.context).inflate(R.layout.news_feed_text_image_social_media_item,p0,false) as View
            val vh= ImageNTextHolder(itemView) as BaseViewHolder
            return vh
        }
        else if(type==HolderType.TEXT_TYPE)
        {
            //Setting the Text News Feed Layout
            Log.e("Type",""+type)
            val itemView=LayoutInflater.from(p0.context).inflate(R.layout.news_feed_text_social_media_item,p0,false) as View
            val vh= TextHolder(itemView) as BaseViewHolder
            return vh

        }
        else if(type==HolderType.IMAGE_TYPE){

            //Setting the Image News Feed Layout
            Log.e("Type",""+type)
            val itemView=LayoutInflater.from(p0.context).inflate(R.layout.news_feed_social_media_item_temp,p0,false) as View
            val vh= ImageHolder(itemView) as BaseViewHolder
            return vh
        }
        else
        {
            //Setting the Loading Layout if the data is null
         val itemView = LayoutInflater.from(p0.context).inflate(R.layout.news_feed_loading_item,p0,false)as View
            return BaseViewHolder(itemView)
        }

    }

    open class BaseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var likeStatus=false
        var commentStatic=false
        val likeButton=itemView.findViewById<LinearLayout>(R.id.likeButton)
        val commentButton=itemView.findViewById<LinearLayout>(R.id.commentButton)
        val likeCount=itemView.findViewById<TextView>(R.id.likeCount)
        val commentCount=itemView.findViewById<TextView>(R.id.commentCount)
        val seenCount=itemView.findViewById<TextView>(R.id.seenCount)
        val likeImage=itemView.findViewById<ImageView>(R.id.likeImage)
        val commentImage=itemView.findViewById<ImageView>(R.id.commentImage)

    }

}