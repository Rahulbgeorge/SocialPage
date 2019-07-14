package com.example.rahul.ounceadmin.View.Helpers.SocialHelperHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.rahul.ounceadmin.R
import com.example.rahul.ounceadmin.View.Helpers.SocialRecyclerAdapter

class ImageNTextHolder(itemView: View):BaseViewHolder(itemView)
{
    val icon= itemView.findViewById<ImageView>(R.id.usericon)
    val mainImage=itemView.findViewById<ImageView>(R.id.mainImage)
    var ProductDescription=itemView.findViewById<TextView>(R.id.product_text)
    val shareButton=itemView.findViewById<ImageView>(R.id.shareImageButton)
}