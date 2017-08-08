package com.example.android.weeklytips;

import com.example.android.weeklytips.model.DataItem;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MyWebService {

    String BASE_URL = "http://560057.youcanlearnit.net/";
    String FEED = "services/json/itemsfeed.php";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET(FEED)
    Call<DataItem[]> dataItems();

}
