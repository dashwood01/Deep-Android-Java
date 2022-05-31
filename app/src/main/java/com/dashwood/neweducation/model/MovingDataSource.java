package com.dashwood.neweducation.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.dashwood.neweducation.inf.Movie;
import com.dashwood.neweducation.inf.Result;
import com.dashwood.neweducation.service.GetMoviesDataService;
import com.dashwood.neweducation.service.RetrofitInstance;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovingDataSource extends PageKeyedDataSource<Integer, Result> {

    private GetMoviesDataService getMoviesDataService;

    public MovingDataSource(GetMoviesDataService getMoviesDataService) {
        this.getMoviesDataService = getMoviesDataService;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {
        Call<Movie> result = getMoviesDataService.getMoviePaging("d87230560436d18e59f8682ab0e85e34", 1);
        result.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.body() == null)
                    return;
                if (response.body().getResults() == null)
                    return;
                Log.i("LOG", "SIZE : " + response.body().getResults().size());
                callback.onResult(response.body().getResults(), null, 2);
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                Log.e("LOG", "LOAD INITIAL");
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {
        Call<Movie> result = getMoviesDataService.getMoviePaging("d87230560436d18e59f8682ab0e85e34", params.key);
        result.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.body() == null)
                    return;
                if (response.body().getResults() == null)
                    return;
                Log.i("LOG", "SIZE : " + response.body().getResults().size());
                callback.onResult(response.body().getResults(), params.key + 1);
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                Log.e("LOG", "LOAD AFTER");
                t.printStackTrace();
            }
        });
    }
}
