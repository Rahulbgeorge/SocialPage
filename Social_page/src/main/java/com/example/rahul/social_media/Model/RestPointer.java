package com.example.rahul.social_media.Model;

import android.util.Log;

import org.json.JSONObject;

public class RestPointer {
    int count=0;
    String next=null,previous=null;

    public RestPointer(String response)
    {
        JSONObject obj;
        try{
            obj=new JSONObject(response);
            count=obj.getInt("count");
            next=obj.getString("next");
            previous=obj.getString("previous");

        }
        catch (Exception e)
        {
            Log.e("Exception occured","Parsing of rest pointer");
        }
    }

    public int getCount(){return this.count;}
    public String getNext(){return this.next;}
    public String getPrevious(){return this.previous;}

}
