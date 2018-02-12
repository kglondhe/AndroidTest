package com.test.androidtest.service.net;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.test.androidtest.service.model.School;
import com.test.androidtest.service.model.SchoolMarks;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    private static DataRepository dataRepository = new DataRepository();

    public static DataRepository instance() {
        return dataRepository;
    }

    public LiveData<List<SchoolMarks>> getSchools() {
        final MutableLiveData<List<SchoolMarks>> liveData = new MutableLiveData<>();
        DataService dataService = RetrofitBuilder.getInstance().create(DataService.class);
        Call<List<SchoolMarks>> call = dataService.getSchools();
        call.enqueue(new Callback<List<SchoolMarks>>() {
            @Override
            public void onResponse(@NonNull Call<List<SchoolMarks>> call, @NonNull Response<List<SchoolMarks>> response) {
                List<SchoolMarks> list = response.body();
                if (list != null) {
                    liveData.setValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<SchoolMarks>> call, @NonNull Throwable t) {
                Log.e(DataRepository.class.getName(), "Error loading films");
            }
        });
        return liveData;
    }

    public LiveData<List<School>> getDetailsSchools() {
        final MutableLiveData<List<School>> liveData = new MutableLiveData<>();
        DataService dataService = RetrofitBuilder.getInstance().create(DataService.class);
        Call<List<School>> call = dataService.getSchoolDetails();
        call.enqueue(new Callback<List<School>>() {
            @Override
            public void onResponse(@NonNull Call<List<School>> call, @NonNull Response<List<School>> response) {
                List<School> list = response.body();
                if (list != null) {
                    liveData.setValue(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<School>> call, @NonNull Throwable t) {
                Log.e(DataRepository.class.getName(), "Error loading films");
            }
        });
        return liveData;
    }
}
