package com.example.rahul.ounceadmin.View.Activities

import android.os.Bundle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.example.rahul.ounceadmin.View.Helpers.IScrollDirection
import com.example.rahul.ounceadmin.R
import com.example.rahul.ounceadmin.Toaster
import com.example.rahul.ounceadmin.View.Helpers.ScrollListener
import com.example.rahul.ounceadmin.View.Helpers.SocialRecyclerAdapter
import com.example.rahul.ounceadmin.ViewModel.SocialViewModel
import kotlinx.android.synthetic.main.activity_home_page.*


import kotlinx.android.synthetic.main.activity_social_page.*


class social_page : AppCompatActivity() {
    var isLoaded:Boolean=false
    var isBottomNavShown:Boolean=false
    var previousSize:Int=0
    lateinit var recyclerAdapter:SocialRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_page)

        //SHOW THE BOTTOM NAVIAGTION BY DEFAULT
        show()

        //INITIALIZING RECYCLER VIEW
        social_recycler.layoutManager= LinearLayoutManager(baseContext, LinearLayout.VERTICAL,false)
        social_recycler.addOnScrollListener(ScrollListener(object : IScrollDirection {
            override fun scrollUp() {
                hide()
            }

            override fun scrollDown() {
                show()
            }

        }))

        //INITIALIZING VIEW MODEL CLASS
        val socialViewModel= ViewModelProviders.of(this).get(SocialViewModel::class.java)
        socialViewModel.init()

        //OBSERVICNG THE NEWS FEED DATA
        socialViewModel.socialData.observe(this, Observer {
            socialData->
                if(!isLoaded) {
                    isLoaded=true
                    previousSize=socialData!!.size
                    recyclerAdapter = SocialRecyclerAdapter(socialData!!, baseContext)
                    social_recycler.adapter = recyclerAdapter
                }else
                {
                    previousSize=socialData!!.size
                    recyclerAdapter.addData(socialData!!)
                    recyclerAdapter.notifyDataSetChanged()
                }
    })


        swipeRefreshLayout.setOnRefreshListener {
            Toaster.tost()

            object:CountDownTimer(1000,1000){
                override fun onFinish() {
                        swipeRefreshLayout.isRefreshing=false
                        Toaster.tost()
                    }

                override fun onTick(p0: Long) {
                }
            }.start()
        }

        newPost.setOnClickListener({
            socialViewModel.addMore()
        })
    }


    //ON SCROLL  DOWN THE BOTTOM NAVIGATION IS HIDDEN
    private fun hide()
    {
        if(isBottomNavShown)
        {
            bottomNav.animate().translationY(100f).setDuration(100).start()
            newPost.animate().translationY(100f).setDuration(100).start()
            isBottomNavShown=false
        }
    }

    //ON SCROLL UP BOTTOM NAVIGATION IS SHOWN
    private fun show()
    {
        if(!isBottomNavShown)
        {
            bottomNav.animate().translationY(0f).setDuration(100).start()
            newPost.animate().translationY(0f).setDuration(100).start()
            isBottomNavShown=true
        }
    }

}
