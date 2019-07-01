package com.example.rahul.ounceadmin.View.Helpers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.rahul.ounceadmin.View.Helpers.NewsListeners.IScrollDirection;

public class ScrollListener extends RecyclerView.OnScrollListener {

    IScrollDirection callback;
    public ScrollListener(IScrollDirection callback)
    {this.callback=callback;}
    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(dy<0) {
        this.callback.scrollDown();
        }else
            this.callback.scrollUp();
    }


    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
