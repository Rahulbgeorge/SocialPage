package com.example.rahul.ounceadmin.View.Helpers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.rahul.ounceadmin.Model.Social
import com.example.rahul.ounceadmin.R

class SocialRecyclerAdapter(var userData:ArrayList<Social>,val mcontext: Context):RecyclerView.Adapter<SocialRecyclerAdapter.ViewHolder>(){


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

    override fun onBindViewHolder(itemHolder: ViewHolder, position: Int) {
        Log.e("Postion called",position.toString())
        val socialData=userData[position]
        if(socialData==null)
        {

        }
        else {
            itemHolder.jewellerName.text = socialData.shopName
            itemHolder.username.text = socialData.username
            itemHolder.likeCount.text = socialData.likeCount.toString()
            itemHolder.commentCount.text = socialData.commentCount.toString()
            Glide.with(mcontext).load(socialData.imageUrl).into(itemHolder.mainImage)
        }


    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        if(userData.get(p1)==null)
        {
            val itemView=LayoutInflater.from(p0.context).inflate(R.layout.loading_item,p0,false) as View
            val vh=ViewHolder(itemView)
            return vh

        }
        else {
            val itemview = LayoutInflater.from(p0.context).inflate(R.layout.social_media_item, p0, false) as View
            val vh = ViewHolder(itemview)
            return vh
        }
    }


    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)
    {
        val icon= itemView.findViewById<ImageView>(R.id.usericon)
        val jewellerName=itemView.findViewById<TextView>(R.id.jewelleryName)
        val username=itemView.findViewById<TextView>(R.id.personName)
        val mainImage=itemView.findViewById<ImageView>(R.id.mainImage)
        val likeButton=itemView.findViewById<ImageView>(R.id.likeButton)
        val commentButton=itemView.findViewById<ImageView>(R.id.commentButton)
        val seenButton=itemView.findViewById<ImageView>(R.id.viewButton)
        val likeCount=itemView.findViewById<TextView>(R.id.likeCount)
        val commentCount=itemView.findViewById<TextView>(R.id.commentCount)
        val seenCount=itemView.findViewById<TextView>(R.id.seenCount)
        val shareButton=itemView.findViewById<ImageView>(R.id.shareImageButton)
    }

    class RefreshHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {

    }

}