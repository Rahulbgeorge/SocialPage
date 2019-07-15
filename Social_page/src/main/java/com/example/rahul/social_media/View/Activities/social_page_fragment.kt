package com.example.rahul.social_media.View.Activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast

import com.example.rahul.social_media.R
import kotlinx.android.synthetic.main.activity_social_page.*
import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.widget.RecyclerView
import com.example.rahul.social_media.View.Helpers.NewsItemListener
import com.example.rahul.social_media.View.Helpers.SocialRecyclerAdapter
import com.example.rahul.social_media.ViewModel.SocialViewModel
import kotlinx.android.synthetic.main.activity_social_page.view.*

class social_page_fragment : Fragment() {
    var isLoaded:Boolean=false
    var isBottomNavShown:Boolean=false
    var previousSize:Int=0
    lateinit var newsItemListener: NewsItemListener
    lateinit var recyclerAdapter: SocialRecyclerAdapter

    lateinit var socialViewModel: SocialViewModel
    lateinit var baseContext: Context

    lateinit var myview:View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       myview=inflater.inflate(R.layout.activity_social_page, container, false)
        Log.e("Social view","started")
        baseContext=myview.context
        //INITIALIZING VIEW MODEL CLASS
        socialViewModel= ViewModelProviders.of(this).get(SocialViewModel::class.java)
        socialViewModel.init()
        newsItemListener= NewsItemListener()

        val social_recycler=myview.findViewById<RecyclerView>(R.id.social_recycler)

        //INITIALIZING RECYCLER VIEW
        social_recycler.layoutManager= LinearLayoutManager(baseContext, LinearLayout.VERTICAL,false)


        //Method to observe News Feed and Load the Old Posts
        observeNewsFeed()

        myview.swipeRefreshLayout.setOnRefreshListener {
            Toast.makeText(baseContext,"Only up refresh remaining", Toast.LENGTH_LONG).show()
            swipeRefreshLayout.isRefreshing=false
        }
        return myview
    }



    private fun observeNewsFeed() {


        //OldPost Listener to add the old news feed
        newsItemListener.setOldPostListener {
            Log.e("custom observer","someone called me in social page")
            Thread(Runnable {
                activity!!.runOnUiThread {
                    socialViewModel.addMore()
                }
            }).start()
        }

        newsItemListener.setOnShopButtonListener{
            news,position->
            Log.e("Shop","Button clicked")

            activity!!.runOnUiThread{
                val intent= Intent(baseContext,ShopPage::class.java)
                intent.putExtra("company",news.ShopId)
                startActivity(intent)
            }

            //startActivity(Intent(baseContext,ShopPage::class.java))
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