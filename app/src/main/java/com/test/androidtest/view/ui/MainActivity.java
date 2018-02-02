package com.test.androidtest.view.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.androidtest.R;
import com.test.androidtest.databinding.ActivityMainBinding;
import com.test.androidtest.service.model.Film;
import com.test.androidtest.view.adapter.FilmAdapter;
import com.test.androidtest.viewmodel.FilmViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private FilmAdapter filmAdapter;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        filmAdapter = new FilmAdapter();
        binding.setIsLoading(true);
        setUpRecyclerView();

        final FilmViewModel filmViewModel =
                ViewModelProviders.of(this).get(FilmViewModel.class);
        observeViewModel(filmViewModel);
    }

    private void observeViewModel(final FilmViewModel filmViewModel) {
        filmViewModel.getFilmLiveData().observeForever(new Observer<List<Film>>() {
            @Override
            public void onChanged(@Nullable List<Film> films) {
                if (films != null) {
                    binding.setIsLoading(false);
                    filmAdapter.setFilms(films);
                    filmViewModel.getFilmLiveData().removeObserver(this);
                }
            }
        });
    }

    private void setUpRecyclerView() {
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        binding.recyclerviewFilms.setLayoutManager(mLayoutManager);
        binding.recyclerviewFilms.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerviewFilms.setAdapter(filmAdapter);
    }
}

