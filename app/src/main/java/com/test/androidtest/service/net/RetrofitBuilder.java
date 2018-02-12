package com.test.androidtest.service.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitBuilder {

    private static final String BASE_URL = "https://data.cityofnewyork.us/resource/";

    private static Retrofit retrofit = null;

    static Retrofit getInstance(){
        if(retrofit == null) {
            retrofit =  new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
