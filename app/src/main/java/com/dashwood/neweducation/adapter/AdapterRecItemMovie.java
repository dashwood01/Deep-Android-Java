package com.dashwood.neweducation.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dashwood.neweducation.databinding.RecItemUserBinding;
import com.dashwood.neweducation.inf.Result;
import com.dashwood.neweducation.service.RetrofitInstance;
import com.dashwood.neweducation.util.MovieAdapterDiffUtilsCallback;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecItemMovie extends RecyclerView.Adapter<AdapterRecItemMovie.ViewHolder> {

    private List<Result> movies = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RecItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setMovieDetail(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void sendItems(List<Result> list) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new MovieAdapterDiffUtilsCallback(list, movies));
        movies = list;
        result.dispatchUpdatesTo(this);
    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(RetrofitInstance.BASE_IMAGE_URL + imageUrl)
                .into(view);
        view.setOnClickListener(v->{
            Log.i("LOG",imageUrl);
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
