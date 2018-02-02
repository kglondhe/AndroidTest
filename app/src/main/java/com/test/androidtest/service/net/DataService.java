package com.test.androidtest.service.net;


import com.test.androidtest.service.model.Films;
import com.test.androidtest.service.model.RetrofitResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataService {

    @GET("films.json")
    Call<RetrofitResponse> getFilms(@Query("limit") int limit);

}
