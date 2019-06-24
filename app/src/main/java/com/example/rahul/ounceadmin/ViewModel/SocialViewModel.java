package com.example.rahul.ounceadmin.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.rahul.ounceadmin.Model.Social;

import java.util.ArrayList;

public class SocialViewModel extends AndroidViewModel {

    MutableLiveData<ArrayList<Social>> socialData;
    ArrayList<Social> data;

    public SocialViewModel(@NonNull Application application) {
        super(application);
    }

    public void init()
    {
        socialData=new MutableLiveData<>();
         data=new ArrayList<>();

        data.add(new Social("231","https://www.franchiseindia.com/uploads/news/fi/voyalla1000x562-102c1e0454.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://odishasuntimes.com/wp-content/uploads/2017/04/jewellery.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://www.ovjjewels.com/pub/media/wysiwyg/south-indian-jewellery.png","","Rahul George","Joy Alukkas",31,2,90));

        data.add(new Social("231","https://www.franchiseindia.com/uploads/news/fi/voyalla1000x562-102c1e0454.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://odishasuntimes.com/wp-content/uploads/2017/04/jewellery.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://www.ovjjewels.com/pub/media/wysiwyg/south-indian-jewellery.png","","Rahul George","Joy Alukkas",31,2,90));

        data.add(new Social("231","https://www.franchiseindia.com/uploads/news/fi/voyalla1000x562-102c1e0454.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://odishasuntimes.com/wp-content/uploads/2017/04/jewellery.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://www.ovjjewels.com/pub/media/wysiwyg/south-indian-jewellery.png","","Rahul George","Joy Alukkas",31,2,90));

        data.add(new Social("231","https://www.franchiseindia.com/uploads/news/fi/voyalla1000x562-102c1e0454.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://odishasuntimes.com/wp-content/uploads/2017/04/jewellery.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://www.ovjjewels.com/pub/media/wysiwyg/south-indian-jewellery.png","","Rahul George","Joy Alukkas",31,2,90));

        data.add(new Social("231","https://www.franchiseindia.com/uploads/news/fi/voyalla1000x562-102c1e0454.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://odishasuntimes.com/wp-content/uploads/2017/04/jewellery.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://www.ovjjewels.com/pub/media/wysiwyg/south-indian-jewellery.png","","Rahul George","Joy Alukkas",31,2,90));

        data.add(new Social("231","https://www.franchiseindia.com/uploads/news/fi/voyalla1000x562-102c1e0454.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://odishasuntimes.com/wp-content/uploads/2017/04/jewellery.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://www.ovjjewels.com/pub/media/wysiwyg/south-indian-jewellery.png","","Rahul George","Joy Alukkas",31,2,90));


        socialData.setValue(data);

    }

    public void addMore()
    {
        data.add(new Social("231","https://www.franchiseindia.com/uploads/news/fi/voyalla1000x562-102c1e0454.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://odishasuntimes.com/wp-content/uploads/2017/04/jewellery.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://www.ovjjewels.com/pub/media/wysiwyg/south-indian-jewellery.png","","Rahul George","Joy Alukkas",31,2,90));

        data.add(new Social("231","https://www.franchiseindia.com/uploads/news/fi/voyalla1000x562-102c1e0454.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://odishasuntimes.com/wp-content/uploads/2017/04/jewellery.jpg","","Rahul George","Joy Alukkas",31,2,90));
        data.add(new Social("231","https://www.ovjjewels.com/pub/media/wysiwyg/south-indian-jewellery.png","","Rahul George","Joy Alukkas",31,2,90));
        socialData.setValue(data);


    }

    public MutableLiveData<ArrayList<Social>> getSocialData(){
        return socialData;
    }
}
