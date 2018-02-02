package com.test.androidtest.service.net;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.test.androidtest.service.model.Film;
import com.test.androidtest.service.model.Films;
import com.test.androidtest.service.model.RetrofitResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private static DataRepository dataRepository = new DataRepository();

    public static DataRepository instance() {
        return dataRepository;
    }

    public LiveData<List<Film>> getFilms() {
        final MutableLiveData<List<Film>> liveData = new MutableLiveData<>();
        DataService dataService = RetrofitBuilder.getInstance().create(DataService.class);
        Call<RetrofitResponse> call = dataService.getFilms(10);
        call.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(@NonNull Call<RetrofitResponse> call, @NonNull Response<RetrofitResponse> response) {
                Films films = response.body().getFilms();
                if (films != null) {
                    liveData.setValue(films.getFilms());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RetrofitResponse> call, @NonNull Throwable t) {
                Log.e(DataRepository.class.getName(), "Error loading films");
            }
        });
        return liveData;
    }
}
