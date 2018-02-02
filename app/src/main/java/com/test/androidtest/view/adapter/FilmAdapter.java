package com.test.androidtest.view.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.test.androidtest.R;

import com.test.androidtest.databinding.FilmItemBinding;
import com.test.androidtest.service.model.Film;

import java.util.ArrayList;
import java.util.List;


public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private List<Film> films;

    static class FilmViewHolder extends RecyclerView.ViewHolder {

        FilmItemBinding binding;

        FilmViewHolder(FilmItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setFilms(final List<Film> films) {
        if (this.films == null) {
            this.films = new ArrayList<>();
            this.films.addAll(films);
            notifyItemRangeInserted(0, this.films.size());
        }
    }


    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final FilmItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.film_item,
                        parent, false);
        return new FilmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final FilmViewHolder holder, int position) {
        holder.binding.setFilm(films.get(position));
        if (films.get(position) != null &&
                films.get(position).getImages() != null &&
                films.get(position).getImages().getImage() != null &&
                films.get(position).getImages().getImage().size() > 0) {
            loadImage(holder.binding.ivFilm, films.get(position).getImages().getImage().get(0).getSrc());
        }
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return films == null ? 0 : films.size();
    }

    public static void loadImage(final ImageView imageView, String imageUrl) {
        final RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.ic_crop_original_black_48dp);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        return false;
                    }
                })
                .apply(requestOptions)
                .thumbnail(0.01f)
                .into(imageView);
    }

}