package com.test.androidtest.service.net;


import com.test.androidtest.service.model.School;
import com.test.androidtest.service.model.SchoolMarks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("734v-jeq5.json")
    Call<List<SchoolMarks>> getSchools();

    @GET("97mf-9njv.json")
    Call<List<School>> getSchoolDetails();

}
