package com.dashwood.neweducation.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dashwood.neweducation.databinding.RecItemUserBinding;
import com.dashwood.neweducation.inf.Movie;
import com.dashwood.neweducation.inf.Result;
import com.dashwood.neweducation.service.RetrofitInstance;
import com.dashwood.neweducation.util.MovieAdapterDiffUtilsCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterRecItemMovie extends PagedListAdapter<Result, AdapterRecItemMovie.ViewHolder> {

    //final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MovieAdapterDiffUtilsCallback(list, movies));

    public AdapterRecItemMovie() {
        super(Movie.CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setMovieDetail(Objects.requireNonNull(getItem(position)));
    }

   /* public void sendItems(List<Result> list) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MovieAdapterDiffUtilsCallback(list, movies));
        movies = list;
        result.dispatchUpdatesTo(this);
    }*/

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(RetrofitInstance.BASE_IMAGE_URL + imageUrl)
                .into(view);
        view.setOnClickListener(v -> {
            Log.i("LOG", imageUrl);
        });
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private final RecItemUserBinding binding;

        public ViewHolder(@NonNull RecItemUserBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
