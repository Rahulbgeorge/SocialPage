package com.example.rahul.ounceadmin.View.Activities

import android.os.Bundle
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.rahul.ounceadmin.Model.Social
import com.example.rahul.ounceadmin.R
import com.example.rahul.ounceadmin.View.Helpers.*
import com.example.rahul.ounceadmin.View.Helpers.NewsListeners.IScrollDirection
import com.example.rahul.ounceadmin.ViewModel.SocialViewModel

import kotlinx.android.synthetic.main.activity_social_page.*

class social_page : AppCompatActivity()   {
    var isLoaded:Boolean=false
    var isBottomNavShown:Boolean=false
    var previousSize:Int=0
    lateinit var newsItemListener: NewsItemListener
    lateinit var recyclerAdapter:SocialRecyclerAdapter

    lateinit var socialViewModel:SocialViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_page)

        //SHOW THE BOTTOM NAVIAGTION BY DEFAULT
        show()
        //INITIALIZING VIEW MODEL CLASS
        socialViewModel= ViewModelProviders.of(this).get(SocialViewModel::class.java)
        socialViewModel.init()
        newsItemListener= NewsItemListener()

        //INITIALIZING RECYCLER VIEW
        social_recycler.layoutManager= LinearLayoutManager(baseContext, LinearLayout.VERTICAL,false)
        social_recycler.addOnScrollListener(ScrollListener(object : IScrollDirection {

            override fun scrollUp() {
                hide()
            }
            override fun scrollDown() {
                show()
            }
        }
        ))

        //Method to observe News Feed and Load the Old Posts
        observeNewsFeed()

        swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(baseContext,"Only up refresh remaining",Toast.LENGTH_LONG).show()
            swipeRefreshLayout.isRefreshing=false
        }

    }

    private fun observeNewsFeed() {

        //OldPost Listener to add the old news feed
        newsItemListener.setOldPostListener {
            Log.e("custom observer","someone called me in social page")
            Thread(Runnable {
                runOnUiThread {
                    socialViewModel.addMore()
                }
            }).start()
        }

        newsItemListener.setOnShopButtonListener{
            news,position->
            Toast.makeText(baseContext,"Shop button clicked",Toast.LENGTH_LONG).show()
        }

        newsItemListener.setOnLikeClickListener {
            news,position->
            socialViewModel.likeNews(news,position)

            Log.e("like","Button clicked")

        }


        //OBSERVING THE NEWS FEED DATA
        socialViewModel.socialData.observe(this, Observer {
            socialData->
            Log.e("social data","size is"+socialData!!.size)
            if(!isLoaded) {
                Log.e("data Fetched","data found")
                isLoaded=true
                previousSize=socialData!!.size
                recyclerAdapter = SocialRecyclerAdapter(socialData!!, baseContext,newsItemListener)
                social_recycler.adapter = recyclerAdapter
            }
            else {
                previousSize=socialData!!.size
                recyclerAdapter.addData(socialData!!)
                recyclerAdapter.notifyDataSetChanged()
            }
        })
    }

    //New Posts Refresh
    private fun NewPosts(){
        //Scroll Top Button is Set to Function
        moveTop.visibility = View.VISIBLE
        moveTop.setOnClickListener {
            moveTop.visibility = View.GONE
            social_recycler.smoothScrollToPosition(0)
        }
    }

    //ON SCROLL  DOWN THE BOTTOM NAVIGATION IS HIDDEN
    private fun hide()
    {
        if(isBottomNavShown)
        {
            social_recycler.animate().translationY(0f).start()
            bottomNav.animate().translationY(100f).setDuration(100).start()
//            newPost.animate().translationY(80f).setDuration(100).start()
            isBottomNavShown=false
        }
    }

    //ON SCROLL UP BOTTOM NAVIGATION IS SHOWN
    private fun show()
    {
        if(!isBottomNavShown)
        {
            social_recycler.animate().translationY(0f).start()
            moveTop.animate().translationY(-20f).start()
            bottomNav.animate().translationY(0f).setDuration(100).start()
//            newPost.animate().translationY(0f).setDuration(100).start()
            isBottomNavShown=true
        }
    }

}




