package com.test.androidtest.view.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.androidtest.R;
import com.test.androidtest.databinding.ActivityMainBinding;
import com.test.androidtest.service.model.SchoolMarks;
import com.test.androidtest.view.adapter.SchoolAdapter;
import com.test.androidtest.viewmodel.SchoolViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private SchoolAdapter schoolAdapter;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        schoolAdapter = new SchoolAdapter();
        binding.setIsLoading(true);
        setUpRecyclerView();

        final SchoolViewModel schoolViewModel =
                ViewModelProviders.of(this).get(SchoolViewModel.class);
        observeViewModel(schoolViewModel);
    }

    private void observeViewModel(final SchoolViewModel schoolViewModel) {
        schoolViewModel.getSchoolLiveData().observeForever(new Observer<List<SchoolMarks>>() {
            @Override
            public void onChanged(@Nullable List<SchoolMarks> schools) {
                if (schools != null) {
                    binding.setIsLoading(false);
                    schoolAdapter.setSchools(schools);
                    schoolViewModel.getSchoolLiveData().removeObserver(this);
                }
            }
        });
    }

    private void setUpRecyclerView() {
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        binding.recyclerviewFilms.setLayoutManager(mLayoutManager);
        binding.recyclerviewFilms.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerviewFilms.setAdapter(schoolAdapter);
    }
}

