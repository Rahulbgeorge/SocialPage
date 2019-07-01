package com.example.rahul.ounceadmin.View.Activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import android.widget.Toast
import com.example.rahul.ounceadmin.*
import com.example.rahul.ounceadmin.Model.API.APIIntefaces.IGenericResponse
import com.example.rahul.ounceadmin.Model.API.FetchNewsFeed
import com.example.rahul.ounceadmin.Model.Social
import com.example.rahul.ounceadmin.View.Helpers.GlideImageLoadingService
import com.example.rahul.ounceadmin.View.Helpers.HomeSliderAdapter
import com.example.rahul.ounceadmin.ViewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home_page.*
import ss.com.bannerslider.Slider

class HomePage : AppCompatActivity() {


   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val viewModel=ViewModelProviders.of(this).get(HomeViewModel::class.java)
       Toaster.init(applicationContext);
       scrollview.fullScroll(ScrollView.FOCUS_UP)
       viewModel.initScreen()

       FetchNewsFeed(object:IGenericResponse<Social>{
           override fun success(out: Social?) {
               Log.e("out","Everything success")
           }

           override fun fail(description: String?) {
                Log.e("out","everything failed")
           }
       })
       //SUMMARY PAGE DATA AND BUTTONS
       viewModel.summary.observe(this, Observer {
           summary->
           saleCount.setText(summary!!.getSalecount().toString())
           purchaseCount.setText(summary!!.getPurchaseCount().toString())
           usercount.setText(summary!!.getUserCount().toString())
           deliveryCount.setText(summary!!.getDeliveryCount().toString())

       })


       //SLIDER DATA AND BUTTONS
       Slider.init(GlideImageLoadingService(this))
       viewModel.ads.observe(this, Observer {
           advertisements->
            advertisement_slider.setAdapter(HomeSliderAdapter().setImages(advertisements))
       })

       //SETTINGS PAGE
       settings.setOnClickListener {
           Log.e("Homepage","settings clicded")
           Toast.makeText(this@HomePage,"Settings clicked",Toast.LENGTH_SHORT).show()
       }

       social.setOnClickListener {
           startActivity(Intent(this@HomePage, social_page::class.java))
       }

       sample.setOnClickListener {
           Toast.makeText(baseContext,"Scrolling should start",Toast.LENGTH_SHORT).show()
//           scrollLayout.scrollTo(0,0)
//           startActivity(Intent(this@HomePage,FullscreenActivity2::class.java))
       }


       Toaster.tost()






    }




}
