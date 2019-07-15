package com.LMJ.ounceapp.Modules.News_feed.View.Activities.Shop_Fragments

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.Toast
import com.LMJ.ounceapp.Modules.News_feed.View.Helpers.Shop_Fragment_Helpers.ReviewRecyclerAdapter
import com.example.rahul.social_media.R
import com.example.rahul.social_media.View.Helpers.ReviewsItemListener
import com.example.rahul.social_media.ViewModel.ShopViewModel

import kotlinx.android.synthetic.main.shop_fragment_reviews.*
import kotlinx.android.synthetic.main.shop_fragment_reviews.view.*

import java.util.ArrayList

class Shop_review : Fragment(), View.OnClickListener {

    internal lateinit var view: View
    lateinit var reviewsItemListener: ReviewsItemListener
    lateinit var recyclerAdapter: ReviewRecyclerAdapter
    lateinit var shopViewModel: ShopViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    fun init(shopViewModel:ShopViewModel)
    {
        this.shopViewModel=shopViewModel
        Log.e("shop review","view model initiailied")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.shop_fragment_reviews, container, false)

        Log.e("shop review","Initializing basics")

        reviewsItemListener = ReviewsItemListener()

        view.review_recycler.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL,false)

        Log.e("shop review","Initializing listeners")
        shopViewModel.company.observe(this, Observer {
            company->
            Log.e("Review recycler","Loading review recycler")

            view.review_recycler.adapter=ReviewRecyclerAdapter(company!!.companyReviews,activity!!.baseContext)
        })
        //Popup Dialog For writing the Review
        val linearLayout = view.findViewById<View>(R.id.rate_review) as LinearLayout
        linearLayout.setOnClickListener {
            val dialog = Dialog(context!!)
            dialog.setContentView(R.layout.shop_review_dialogbox)

            val ratingBar = dialog.findViewById<View>(R.id.rating) as RatingBar
            ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser -> Log.e("Rating", "" + rating) }

            val c = dialog.findViewById<CardView>(R.id.F_button)
            c.setOnClickListener {
                val review = dialog.findViewById<EditText>(R.id.et_review)

                val Review = review.text.toString()

                Log.e("Review", "" + Review)

                if (review.text.toString().isEmpty()) {
                    Toast.makeText(context, "Please fill in some review", Toast.LENGTH_SHORT).show()
                } else {
                    dialog.dismiss()
                }
            }
            dialog.setCancelable(false)
            dialog.show()

            dialog.setCancelable(false)
            dialog.show()

            val iv = dialog.findViewById<ImageView>(R.id.cancel)
            iv.setOnClickListener { dialog.dismiss() }


        }

        return view
    }

    override fun onClick(v: View) {

    }

}
