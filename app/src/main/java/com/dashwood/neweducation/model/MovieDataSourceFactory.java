package com.dashwood.neweducation.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;

import com.dashwood.neweducation.inf.Result;
import com.dashwood.neweducation.service.GetMoviesDataService;

import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Result> {
//public class MovieDataSourceFactory extends PagingSource<Integer, Result> {

    private final MutableLiveData<MovingDataSource> movingDataSourceMutableLiveData;
    private final GetMoviesDataService getMoviesDataService;

    public MovieDataSourceFactory(GetMoviesDataService getMoviesDataService) {
        this.getMoviesDataService = getMoviesDataService;
        movingDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        MovingDataSource movingDataSource = new MovingDataSource(getMoviesDataService);
        movingDataSourceMutableLiveData.postValue(movingDataSource);
        return movingDataSource;
    }

    public MutableLiveData<MovingDataSource> getMovingDataSourceMutableLiveData() {
        return movingDataSourceMutableLiveData;
    }

   /* @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Result> pagingState) {
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, Result> anchorPage = pagingState.closestPageToPosition(anchorPosition);
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
    }

    @Nullable
    @Override
    public Object load(@NonNull LoadParams<Integer> loadParams, @NonNull Continuation<? super LoadResult<Integer, Result>> continuation) {
        MovingDataSource movingDataSource = new MovingDataSource(getMoviesDataService);
        movingDataSourceMutableLiveData.postValue(movingDataSource);
        return movingDataSource;
    }*/
}
