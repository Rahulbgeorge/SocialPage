package com.example.rahul.ounceadmin.Model.API;

import android.util.Log;

import com.example.rahul.ounceadmin.Model.API.APIIntefaces.IGenericResponse;
import com.example.rahul.ounceadmin.Model.API.APIIntefaces.IHttpResponse;
import com.example.rahul.ounceadmin.Model.InternalTools.Response;

public class LikeNewsFeed implements IHttpResponse {

    IGenericResponse<String> response;
    public LikeNewsFeed(String token, String id, IGenericResponse<String> response)
    {
        this.response=response;
        new HttpRequest()
                .addUrl("news/like/"+id+"/")
                .addHeader("Authorization","Token 6d3874c0a2125ce600adb4ad93a96f414f92ce26")
                .get(this);
    }

    @Override
    public void dataFetched(Response response) {
        if(response.getStatusCode()==200)
        {
            Log.e("Response data",response.getData());
        }
        else
        {
            Log.e("Response data","Exception occured"+response.getStatusCode());
        }
    }
}
