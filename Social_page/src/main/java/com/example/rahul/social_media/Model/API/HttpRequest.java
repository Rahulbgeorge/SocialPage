package com.example.rahul.social_media.Model.API;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.example.rahul.social_media.Model.API.APIIntefaces.IHttpResponse;
import com.example.rahul.social_media.Model.InternalTools.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequest {
    //   String url="http://192.168.137.166/";
//    String url="http://34.93.129.115/";
    String url="http:/192.168.0.6/";
//    String url="http://test.1ounce.in/api/";

//    String url="http://192.168.43.2/";
//    String url="https://mobile.1ounce.in/";
//    String url="http://192.168 .2.6/";


    String data;
    Map<String,Object> params;
    Map<String,String> header;
    Activity activity;
    IHttpResponse responseActivity;
    String requestType="POST";

    public HttpRequest()
    {
        params= new LinkedHashMap<>();
        header=new LinkedHashMap<>();
    }


    public HttpRequest addParameter(String key,String value)
    {
        params.put(key,value);
        return this;
    }

    public HttpRequest addParameter(String key, Object value)
    {
        params.put(key,value);
        return this;
    }

    public HttpRequest addUrl(String url)
    {
        this.url=this.url+url;
        return this;
    }

    public HttpRequest addBaseUrl(String url)
    {
        this.url=url;
        return this;
    }

    public HttpRequest addHeader(String name,String value)
    {
        header.put(name,value);
        return this;
    }



    public void post(IHttpResponse activity)
    {
        responseActivity=activity;
        new Downloader().execute();
    }


    public void put(IHttpResponse activity)
    {
        this.requestType="PUT";
        responseActivity=activity;
        new Downloader().execute();
    }

    public void patch(IHttpResponse actvity)
    {
        this.requestType="PATCH";
        responseActivity=actvity;
        new Downloader().execute();
    }


    public void get(IHttpResponse activity)
    {
        responseActivity=activity;
        this.requestType="GET";
        new Downloader().execute();
    }

    private Response makeGet()
    {
        try {

            URL mUrl = new URL(this.url);
            HttpURLConnection httpConnection = (HttpURLConnection) mUrl.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-length", "0");
            Log.e("setting","reached here");
            for (Map.Entry<String, String> param : header.entrySet()) {
                Log.e("setting","header value"+param.getValue());
                httpConnection.setRequestProperty(param.getKey(),param.getValue());
            }
            httpConnection.setUseCaches(false);
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setConnectTimeout(100000);
            httpConnection.setReadTimeout(100000);

            httpConnection.connect();

            int responseCode = httpConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();

                return new Response(responseCode,httpConnection.getResponseMessage(),sb.toString());
            }
        }
        catch (Exception e)
        {}
        return new Response(1,null,null);
    }

    private Response makePost(){
        Response response;
        StringBuilder postData = new StringBuilder();
        try {
            Log.e("Downloadinig",this.url);
            URL url = new URL(this.url);

            for (Map.Entry<String, Object> param : params.entrySet()) {
                Log.e("paramter",param.getKey()+":"+param.getValue());
                if (postData.length() != 0) postData.append('&');

                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(requestType);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            for (Map.Entry<String, String> param : header.entrySet()) {
                conn.setRequestProperty(param.getKey(),param.getValue());
            }
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);

            conn.getOutputStream().write(postDataBytes);
            int responseCode=conn.getResponseCode();
            String responseMessage=""+conn.getResponseMessage();
            Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (int c; (c = in.read()) >= 0; )
                sb.append((char) c);
            String out = sb.toString();

            response=new Response(responseCode,responseMessage,out);

            return response;

        }
        catch(Exception e)
        {
            Log.e("HttpRequest","exception occured"+e.getMessage());
            JSONObject obj=new JSONObject();
            try {
                obj.put("result","fail");
                obj.put("description","Unable to connect to server. Please check your Internet");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            response=new Response(0,null,null);
            return response;


        }
    }

    class Downloader extends AsyncTask<Void,Response,Response>
    {
        @Override
        protected Response doInBackground(Void... voids) {

            if(requestType.equals("GET"))
            {return makeGet();}
            else
            {
                return makePost();
            }
        }

        @Override
        protected void onPostExecute(Response response) {
            responseActivity.dataFetched(response);
        }
    }


    public void postImage()
    {}



}



