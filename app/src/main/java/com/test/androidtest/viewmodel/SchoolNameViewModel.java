package com.test.androidtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.test.androidtest.service.model.School;
import com.test.androidtest.service.model.SchoolMarks;
import com.test.androidtest.service.net.DataRepository;

import java.util.List;


public class SchoolNameViewModel extends AndroidViewModel {

    private LiveData<List<School>> schoolLiveData;

    public SchoolNameViewModel(final Application application) {
        super(application);
        initNetworkData();
    }

    private void initNetworkData() {
        DataRepository dataRepository = DataRepository.instance();
        schoolLiveData = dataRepository.getDetailsSchools();
    }

    public LiveData<List<School>> getSchoolLiveData() {
        return schoolLiveData;
    }

}
