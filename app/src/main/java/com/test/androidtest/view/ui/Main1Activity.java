package com.test.androidtest.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.test.androidtest.R;
import com.test.androidtest.databinding.ActivityMain1Binding;
import com.test.androidtest.databinding.ActivityMainBinding;
import com.test.androidtest.service.model.School;
import com.test.androidtest.service.model.SchoolMarks;
import com.test.androidtest.view.CallBack;
import com.test.androidtest.view.adapter.SchoolAdapter;
import com.test.androidtest.view.adapter.SchoolNameAdapter;
import com.test.androidtest.viewmodel.SchoolNameViewModel;
import com.test.androidtest.viewmodel.SchoolViewModel;

import java.util.List;

public class Main1Activity extends AppCompatActivity implements CallBack{

    private SchoolNameAdapter schoolAdapter;

    private ActivityMain1Binding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main1);
        schoolAdapter = new SchoolNameAdapter(this);
        binding.setIsLoading(true);
        setUpRecyclerView();

        final SchoolNameViewModel schoolViewModel =
                ViewModelProviders.of(this).get(SchoolNameViewModel.class);
        observeViewModel(schoolViewModel);
    }

    private void observeViewModel(final SchoolNameViewModel schoolViewModel) {
        schoolViewModel.getSchoolLiveData().observeForever(new Observer<List<School>>() {
            @Override
            public void onChanged(@Nullable List<School> schools) {
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

    @Override
    public void onClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
