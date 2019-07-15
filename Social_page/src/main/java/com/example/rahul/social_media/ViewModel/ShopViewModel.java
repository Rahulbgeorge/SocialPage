package com.example.rahul.social_media.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;


import com.example.rahul.social_media.Model.API.APIIntefaces.IBaseUrl;
import com.example.rahul.social_media.Model.API.APIIntefaces.ICompanyReviewAPI;
import com.example.rahul.social_media.Model.API.Company;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopViewModel extends AndroidViewModel {

    MutableLiveData<Company> company;
    int companyId;

    public ShopViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(Intent intent) {
        companyId=intent.getExtras().getInt("company");
        //Fetching company related data from api
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IBaseUrl.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        company=new MutableLiveData<>();
        ICompanyReviewAPI api=retrofit.create(ICompanyReviewAPI.class);
        Call<Company> call=api.getResponseData(companyId+"");
        call.enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                company.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {
                Log.e("Retrofit failure","failed"+t.getMessage());
            }
        });




    }

    public MutableLiveData<Company> getCompany() {return company; }
}
