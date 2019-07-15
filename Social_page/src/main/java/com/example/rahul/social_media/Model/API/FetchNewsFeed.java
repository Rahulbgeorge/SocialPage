package com.example.rahul.social_media.Model.API;

import android.util.Log;

import com.example.rahul.social_media.Model.API.APIIntefaces.IGenericResponse;
import com.example.rahul.social_media.Model.API.APIIntefaces.IHttpResponse;
import com.example.rahul.social_media.Model.InternalTools.Response;
import com.example.rahul.social_media.Model.RestPointer;
import com.example.rahul.social_media.Model.Social;

import org.json.JSONArray;
import org.json.JSONObject;
import static com.example.rahul.social_media.ViewModel.SocialViewModel.restPointer;

import java.util.ArrayList;

public class FetchNewsFeed implements IHttpResponse {
    IGenericResponse<ArrayList<Social>> response;

    public FetchNewsFeed(String nextPageLink, IGenericResponse<ArrayList<Social>> response)
    {
        this.response=response;
        new HttpRequest()
                .addBaseUrl(nextPageLink)
                .get(this);
    }

    public FetchNewsFeed(IGenericResponse response)
    {
        this.response=response;
        new HttpRequest()
                .addUrl("news/allNews/")
                .get(this);
    }

    @Override
    public void dataFetched(Response response) {
        Log.e("output",response.getStatusCode()+"");
        Log.e("output",response.getStatusMessage()+"");
        Log.e("output",response.getData()+"");
        if(response.getStatusCode()==200)
        {
            restPointer=new RestPointer(response.getData());
            //parsing input
            try {
                ArrayList<Social> newsFeed=new ArrayList<>();
                JSONObject obj = new JSONObject(response.getData());
                JSONObject company;
                JSONArray newsList=(JSONArray) obj.get("results");
                for(int i=0;i<newsList.length();i++)
                {
                    obj=(JSONObject) newsList.get(i);
                    company=(JSONObject)obj.get("company");
                    Log.e("company id",company.getString("id"));
                    newsFeed.add(
                    new Social(obj.getString("id"),
                            obj.getString("imageUrl"),
                            company.getString("dealer_image"),
                            company.getString("user"),
                            company.getString("name"),
                            obj.getInt("like_count"),
                            obj.getInt("comment_count"),
                            obj.getInt("seen_count"),
                            obj.getString("description"),
                            company.getString("id"))
                    );

                }
                Log.e("HTTP call","everything working");
                this.response.success(newsFeed);


            }
            catch (Exception e)
            {
                this.response.fail(e.getMessage());
                Log.e("Exception occured","Exception"+e.getMessage());
            }
            }
    }
}
