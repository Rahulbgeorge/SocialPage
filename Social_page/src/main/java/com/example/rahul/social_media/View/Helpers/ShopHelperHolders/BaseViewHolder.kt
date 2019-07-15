package com.example.rahul.social_media.View.Helpers.ShopHelperHolders;

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.example.rahul.social_media.R
import org.w3c.dom.Text

open class BaseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    var review_likeStatus=false

    val review = itemView.findViewById<TextView>(R.id.comment)
    val review_likeButton=itemView.findViewById<ImageView>(R.id.Comment_likeButton)
    val review_likeCount=itemView.findViewById<TextView>(R.id.like_count)
    val review_jewelleryUser=itemView.findViewById<TextView>(R.id.user_name)
    val review_duration = itemView.findViewById<TextView>(R.id.duration)
    val review_duration_type =itemView.findViewById<TextView>(R.id.duration_type)
}