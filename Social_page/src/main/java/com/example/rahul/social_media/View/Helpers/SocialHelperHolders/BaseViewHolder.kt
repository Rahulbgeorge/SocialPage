package com.example.rahul.social_media.View.Helpers.SocialHelperHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.rahul.social_media.R

open class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    var likeStatus=false
    var commentStatic=false
    val likeButton=itemView.findViewById<LinearLayout>(R.id.likeButton)
    val shopButton=itemView.findViewById<LinearLayout>(R.id.shopButton)
    val likeCount=itemView.findViewById<TextView>(R.id.likeCount)
    val seenCount=itemView.findViewById<TextView>(R.id.seenCount)
    val likeImage=itemView.findViewById<ImageView>(R.id.likeImage)
    val shopImage=itemView.findViewById<ImageView>(R.id.shopImage)
    val jewelleryName=itemView.findViewById<TextView>(R.id.jewelleryName)
    val jewelleryUser=itemView.findViewById<TextView>(R.id.personName)
}