package com.example.rahul.ounceadmin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.rahul.ounceadmin.Model.API.APIIntefaces.IGenericResponse;
import com.example.rahul.ounceadmin.Model.API.FetchNewsFeed;
import com.example.rahul.ounceadmin.Model.API.LikeNewsFeed;
import com.example.rahul.ounceadmin.Model.InternalTools.NewsFeedParser;
import com.example.rahul.ounceadmin.Model.RestPointer;
import com.example.rahul.ounceadmin.Model.Social;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SocialViewModel extends AndroidViewModel {
    public static RestPointer restPointer=null;
    MutableLiveData<ArrayList<Social>> socialData;
    ArrayList<Social> data;

    public SocialViewModel(@NonNull Application application) {
        super(application);
    }

    public void init()
    {
        socialData=new MutableLiveData<>();
        data=new ArrayList<>();
        //first time news feed fetch
       new FetchNewsFeed(new IGenericResponse<ArrayList<Social>>(){
           @Override
           public void success(ArrayList<Social> out) {
               Log.e("Success","Data fetched"+out.size());
               out.add(null);
               socialData.setValue(out);
           }

           @Override
           public void fail(String description) {
            Log.e("social view model","failed");
           }
       });

    }

    public void addMore()
    {
        new FetchNewsFeed(restPointer.getNext(), new IGenericResponse<ArrayList<Social>>() {
            @Override
            public void success(ArrayList<Social> out) {

                ArrayList<Social> currentdata=socialData.getValue();
                currentdata.remove(null);
                currentdata.addAll(currentdata.size(),out);
                if(!(restPointer.getNext()==null || restPointer.getNext().equals("null")))
                {
                    currentdata.add(null);


                }
                socialData.setValue(currentdata);
            }

            @Override
            public void fail(String description) {

            }
        });


    }

    public MutableLiveData<ArrayList<Social>> getSocialData(){
        return socialData;
    }

    public void likeNews(Social social,int position)
    {
        Log.e("API Call","Triggered");
        new LikeNewsFeed("", social.id, new IGenericResponse<String>() {
        @Override
        public void success(String out) {
            Log.e("Like Response",out);
        }

        @Override
        public void fail(String description) {
            Log.e("Like Response",description);
        }
    });
    }
}
