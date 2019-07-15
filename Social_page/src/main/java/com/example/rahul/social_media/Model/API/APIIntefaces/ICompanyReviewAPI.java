package com.example.rahul.social_media.Model.API.APIIntefaces;

import com.example.rahul.social_media.Model.API.Company;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICompanyReviewAPI {
    String baseUrl=IBaseUrl.url;

    @GET("/company/review/{company_id}/")
    Call<Company> getResponseData(@Path(value = "company_id", encoded = true) String companyid);

}
