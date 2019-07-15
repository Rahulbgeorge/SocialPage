package com.LMJ.ounceapp.Modules.News_feed.View.Activities.Shop_Fragments

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.example.rahul.social_media.R
import com.example.rahul.social_media.View.Helpers.Shop_Fragment_Helpers.Shop_Gallery_Adapter
import com.example.rahul.social_media.ViewModel.ShopViewModel
import kotlinx.android.synthetic.main.shop_fragment_gallery.*
import kotlinx.android.synthetic.main.shop_fragment_gallery.view.*
import java.util.*


class Shop_Gallery : Fragment(), View.OnClickListener {

    internal lateinit var view: View



    internal lateinit var mRecyclerView: RecyclerView
    internal lateinit var modelslist: IntArray
    lateinit var shopViewModel: ShopViewModel

    fun init(shopViewModel: ShopViewModel)
    {
        this.shopViewModel=shopViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.shop_fragment_gallery, container, false)

        //INITIALIZING VIEW MODEL CLASS
//        galleryViewModel = ViewModelProviders.of(this).get(galleryViewModel::class.java)
//        galleryViewModel.init()

        //INITIALIZING RECYCLER VIEW
        view.gallery_recycler.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)



        mRecyclerView = view.findViewById(R.id.gallery_recycler)
        val manager = GridLayoutManager(context, 3)
        mRecyclerView.layoutManager = manager




        shopViewModel.company.observe(this,android.arch.lifecycle.Observer {
            //todo initialize gallery over here
            company->
            val test_gallery_adapter = Shop_Gallery_Adapter(context, company!!.companyPostOverviews)
            mRecyclerView.adapter = test_gallery_adapter
        })

        return view
    }

    override fun onClick(v: View) {

    }

}
