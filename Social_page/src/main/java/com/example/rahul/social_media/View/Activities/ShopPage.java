package com.example.rahul.social_media.View.Activities;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.LMJ.ounceapp.Modules.News_feed.View.Activities.Shop_Fragments.Shop_Contact;
import com.LMJ.ounceapp.Modules.News_feed.View.Activities.Shop_Fragments.Shop_Gallery;
import com.LMJ.ounceapp.Modules.News_feed.View.Activities.Shop_Fragments.Shop_review;
import com.bumptech.glide.Glide;
import com.example.rahul.social_media.Model.API.Company;
import com.example.rahul.social_media.R;
import com.example.rahul.social_media.ViewModel.ShopViewModel;

import java.util.ArrayList;
import java.util.List;


public class ShopPage extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ShopViewModel shopViewModel;
    TextView iv;
    ImageView shopImage;
    TextView shopName,location,avgRating;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        declareShopVariable();

        shopViewModel=new ShopViewModel(getApplication());



        LinearLayout back =   findViewById(R.id.backbutton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView goBack = (TextView) findViewById(R.id.back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewPager = (ViewPager) findViewById(R.id.shop_tabs);
        setupViewPager(viewPager);
        //view model initialization
        shopViewModel.init(getIntent());
        shopViewModel.getCompany().observe(this, new Observer<Company>() {
            @Override
            public void onChanged(@Nullable Company company) {
                if(company!=null)
                {
                    loadBasicShopData(company);
                }
            }
        });
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);




    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Shop_Gallery gallery=new Shop_Gallery();
        Shop_review review=new Shop_review();
        Shop_Contact contact=new Shop_Contact();
        review.init(shopViewModel);
        contact.init(shopViewModel);
        gallery.init(shopViewModel);
        adapter.addFragment(gallery, "Gallery");
        adapter.addFragment(review, "Review");
        adapter.addFragment(contact,"Contact");
        viewPager.setAdapter(adapter);
    }

    public void backClicked(View view){
        Log.e("Clicked","Done");
}

    private void declareShopVariable()
    {
        shopName=findViewById(R.id.storename);

        shopImage=findViewById(R.id.shop_image);
        location=findViewById(R.id.location);

    }

    private void loadBasicShopData(Company company)
    {
        Log.e("comapany name","Company name is "+company.getCompanyName());
        shopName.setText(company.getCompanyName());
        location.setText(company.getAddress());
        Glide.with(this)
            .load(company.getCompanyImage())
            .into(shopImage);
        Log.e("company data","company location"+company.getAddress());
    }



    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



}
