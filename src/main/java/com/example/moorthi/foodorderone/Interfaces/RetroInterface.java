package com.example.moorthi.foodorderone.Interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by moorthy on 11/20/18.
 */

public interface RetroInterface {
    @GET("data.json" )
    Call<ResponseBody> getFoodDetails();
}
