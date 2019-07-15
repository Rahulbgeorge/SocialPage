package com.example.rahul.social_media.Model.API;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Company {

    @SerializedName("name")
    String name;
    @SerializedName("dealer_image")
    String companyImage;


    float avg_rating;

    @SerializedName("address")
    String address;

    @SerializedName("phone")
    String contact;

    ArrayList<String> companyUploads;

    @SerializedName("companyReview")
    List<CompanyReview> reviews;

    @SerializedName("CompanyRelatedNews")
    List<CompanyPostOverview> companyPostOverviews;

    public Company(String name,String companyImage,float avgRating)
    {
        this.name=name;
        this.companyImage=companyImage;
        this.avg_rating=avgRating;
        companyUploads=new ArrayList<>();
        reviews=new ArrayList<>();
    }


    public List<CompanyPostOverview> getCompanyPostOverviews(){return companyPostOverviews;}
    public void setCompanyUploads(ArrayList<String> companyUploads) {this.companyUploads=companyUploads;}
    public void setCOmpanyReview(ArrayList<CompanyReview> reviews){this.reviews=reviews;}

    public String getCompanyName(){return name;}
    public String getCompanyImage(){return companyImage;}
    public float getAvgRating(){return avg_rating;}
    public ArrayList<String> getUploadedImages(){return companyUploads;}
    public ArrayList<CompanyReview> getCompanyReviews(){return (ArrayList<CompanyReview>)reviews;}
    public String getContact(){return contact;}
    public String getAddress(){return address;}
}
