package com.dashwood.neweducation.model;

import android.renderscript.Sampler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.Key;
import androidx.paging.DataSource;
import androidx.paging.ListenableFuturePagingSource;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;

import com.dashwood.neweducation.inf.Movie;
import com.dashwood.neweducation.inf.Result;
import com.dashwood.neweducation.service.GetMoviesDataService;
import com.dashwood.neweducation.service.RetrofitInstance;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import kotlin.coroutines.Continuation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class MovingDataSource extends PageKeyedDataSource<Integer, Result> {
//public class MovingDataSource extends ListenableFuturePagingSource<Integer, Result> {
    @NonNull
    private GetMoviesDataService getMoviesDataService;

    public MovingDataSource(GetMoviesDataService getMoviesDataService) {
        this.getMoviesDataService = getMoviesDataService;
    }

   /* @NonNull
    @Override
    public ListenableFuture<LoadResult<Integer, Result>> loadFuture(@NonNull LoadParams<Integer> loadParams) {
        Integer nextPageNumber = loadParams.getKey();
        if (nextPageNumber == null) {
            nextPageNumber = 1;
        }

        ListenableFuture<LoadResult<Integer, Result>> pageFuture =
                Futures.transform(getMoviesDataService.getMoviePaging("d87230560436d18e59f8682ab0e85e34", nextPageNumber),
                        this::toLoadResult, mBgExecutor);

        ListenableFuture<LoadResult<Integer, Result>> partialLoadResultFuture =
                Futures.catching(pageFuture, HttpException.class,
                        LoadResult.Error::new, mBgExecutor);

        return Futures.catching(partialLoadResultFuture,
                IOException.class, LoadResult.Error::new, mBgExecutor);
    }

    private LoadResult<Integer, Result> toLoadResult(@NonNull Movie response) {
        return new LoadResult.Page<>(response.getResults(),
                null, // Only paging forward.
                response.getPage(),
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NotNull PagingState<Integer, Result> state) {
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, Result> anchorPage = state.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }

        return null;
    }*/

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
