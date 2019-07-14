package com.example.rahul.ounceadmin.View.Helpers.SocialHelperHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.rahul.ounceadmin.R
import com.example.rahul.ounceadmin.View.Helpers.SocialRecyclerAdapter

//Creating a TextHolder Which helps to set the SocialAdapter with Text layout which is connected to SocialRecyclerAdapter.The layout type is set in Model Package - Social.Java
class TextHolder(itemView: View): BaseViewHolder(itemView)
{
    val icon= itemView.findViewById<ImageView>(R.id.usericon)
    var ProductDescription=itemView.findViewById<TextView>(R.id.product_text)
    val shareButton=itemView.findViewById<ImageView>(R.id.shareImageButton)

}
