package com.test.androidtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.test.androidtest.service.model.Film;
import com.test.androidtest.service.model.Films;
import com.test.androidtest.service.net.DataRepository;

import java.util.List;


public class FilmViewModel extends AndroidViewModel {

    private LiveData<List<Film>> filmLiveData;

    public FilmViewModel(final Application application) {
        super(application);
        initNetworkData();
    }

    private void initNetworkData() {
        DataRepository dataRepository = DataRepository.instance();
        filmLiveData = dataRepository.getFilms();
    }

    public LiveData<List<Film>> getFilmLiveData() {
        return filmLiveData;
    }

}
