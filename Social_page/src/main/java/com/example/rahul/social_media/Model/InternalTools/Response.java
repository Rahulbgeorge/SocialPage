package com.example.rahul.social_media.Model.InternalTools;

public class Response {
    int status_code;
    String status_message;
    String data;
    public Response(int status_code,String status_message,String data)
    {
        this.status_code=status_code;
        this.status_message=status_message;
        this.data=data;
    }

    public int getStatusCode(){return status_code;}
    public String getStatusMessage(){return status_message;}
    public String getData(){return data;}
}
