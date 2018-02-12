package com.test.androidtest.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.androidtest.R;

import com.test.androidtest.databinding.SchoolItemBinding;
import com.test.androidtest.service.model.SchoolMarks;

import java.util.ArrayList;
import java.util.List;


public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> {

    private List<SchoolMarks> schools;

    static class SchoolViewHolder extends RecyclerView.ViewHolder {

        SchoolItemBinding binding;

        SchoolViewHolder(SchoolItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setSchools(final List<SchoolMarks> schools) {
        if (this.schools == null) {
            this.schools = new ArrayList<>();
            this.schools.addAll(schools);
            notifyItemRangeInserted(0, this.schools.size());
        }
    }


    @Override
    public SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final SchoolItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.school_item,
                        parent, false);
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