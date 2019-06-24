package com.example.rahul.ounceadmin;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Toaster {
    static Context context;
    static Toaster instance;

    private Toaster(Context context)
    {this.context=context;}

    public static void init(Context context)
    {
        if(instance==null)
        {
            Log.e("Instance created","True");
            instance=new Toaster(context);
        }
    }

    public static void tost()
    {
        Toast.makeText(context,"This is my toast message",Toast.LENGTH_LONG).show();
    }
}
