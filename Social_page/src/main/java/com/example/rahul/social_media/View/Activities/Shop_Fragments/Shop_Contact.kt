package com.LMJ.ounceapp.Modules.News_feed.View.Activities.Shop_Fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rahul.social_media.R
import com.example.rahul.social_media.ViewModel.ShopViewModel
import kotlinx.android.synthetic.main.shop_contact_item.view.*


class Shop_Contact : Fragment(), View.OnClickListener {

    internal lateinit var view: View
    lateinit var shopViewModel: ShopViewModel

    fun init(shopViewModel:ShopViewModel)
    {this.shopViewModel=shopViewModel;}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.shop_fragment_contact, container, false)



        shopViewModel.company.observe(this,android.arch.lifecycle.Observer {
            company->
                view.contact_number.text=company!!.contact
                view.address.text=company!!.address
            //todo company related data to be initialized here
        })


        return view
    }


    override fun onClick(v: View) {

    }
}
