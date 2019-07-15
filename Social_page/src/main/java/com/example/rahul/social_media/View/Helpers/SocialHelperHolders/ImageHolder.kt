package com.example.rahul.social_media.View.Helpers.SocialHelperHolders

import android.view.View
import android.widget.ImageView
import com.example.rahul.social_media.R

//Creating a ImageHolder Which helps to set the SocialAdapter with Image layout which is connected to SocialRecyclerAdapter. The layout type is set in Model Package - Social.Java
class ImageHolder(itemView : View): BaseViewHolder(itemView)
{
    val icon= itemView.findViewById<ImageView>(R.id.usericon)
    val mainImage=itemView.findViewById<ImageView>(R.id.mainImage)
    val shareButton=itemView.findViewById<ImageView>(R.id.shareImageButton)
}