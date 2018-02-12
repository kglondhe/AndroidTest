package com.test.androidtest.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.androidtest.R;
import com.test.androidtest.databinding.SchoolNameItemBinding;
import com.test.androidtest.service.model.School;
import com.test.androidtest.service.model.SchoolMarks;
import com.test.androidtest.view.CallBack;

import java.util.ArrayList;
import java.util.List;


public class SchoolNameAdapter extends RecyclerView.Adapter<SchoolNameAdapter.SchoolViewHolder> {

    private List<School> schools;
    CallBack callBack;

    public SchoolNameAdapter(CallBack callBack) {
        this.callBack = callBack;
    }

    static class SchoolViewHolder extends RecyclerView.ViewHolder {

        SchoolNameItemBinding binding;

        SchoolViewHolder(SchoolNameItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setSchools(final List<School> schools) {
        if (this.schools == null) {
            this.schools = new ArrayList<>();
            this.schools.addAll(schools);
            notifyItemRangeInserted(0, this.schools.size());
        }
    }


    @Override
    public SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final SchoolNameItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.school_name_item,
                        parent, false);
        binding.setCallback(callBack);
        return new SchoolViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final SchoolViewHolder holder, int position) {
        holder.binding.setSchool(schools.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return schools == null ? 0 : schools.size();
    }

}