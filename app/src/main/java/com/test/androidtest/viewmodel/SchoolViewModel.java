package com.test.androidtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.test.androidtest.service.model.SchoolMarks;
import com.test.androidtest.service.net.DataRepository;

import java.util.List;


public class SchoolViewModel extends AndroidViewModel {

    private LiveData<List<SchoolMarks>> schoolLiveData;

    public SchoolViewModel(final Application application) {
        super(application);
        initNetworkData();
    }

    private void initNetworkData() {
        DataRepository dataRepository = DataRepository.instance();
        schoolLiveData = dataRepository.getSchools();
    }

    public LiveData<List<SchoolMarks>> getSchoolLiveData() {
        return schoolLiveData;
    }

}
