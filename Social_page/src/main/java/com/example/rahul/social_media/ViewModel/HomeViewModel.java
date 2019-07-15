package com.example.rahul.social_media.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.rahul.social_media.Model.Advertisement;
import com.example.rahul.social_media.Model.Summary;

import java.util.ArrayList;

public class HomeViewModel extends AndroidViewModel {
    MutableLiveData<Summary> summary;
    MutableLiveData<ArrayList<Advertisement>> ads;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        summary=new MutableLiveData<>();
        ads=new MutableLiveData<>();
    }

    public void initScreen()
    {
        //should be initialized with data fetcher
        Summary summary=new Summary();
        summary.deliveryCount=20;
        summary.purchaseCount=32;
        summary.salecount=21;
        summary.userCount=10;
        this.summary.setValue(summary);
        ArrayList<Advertisement> ads=new ArrayList<Advertisement>();
        ads.add(new Advertisement("https://qrius.com/wp-content/uploads/2014/03/Highway-Movie-Poster.jpg"));
        ads.add(new Advertisement("https://houseofgeekery.files.wordpress.com/2017/12/the_avengers_poster-banner.jpg"));
        this.ads.setValue(ads);

    }

    public MutableLiveData<Summary> getSummary()
    {
        return summary;
    }

    public MutableLiveData<ArrayList<Advertisement>> getAds(){return ads;}
}
