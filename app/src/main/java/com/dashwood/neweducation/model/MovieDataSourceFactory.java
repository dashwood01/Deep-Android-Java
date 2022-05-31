package com.dashwood.neweducation.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.dashwood.neweducation.inf.Result;
import com.dashwood.neweducation.service.GetMoviesDataService;

public class MovieDataSourceFactory extends DataSource.Factory<Integer, Result> {

    private MovingDataSource movingDataSource;
    private final MutableLiveData<MovingDataSource> movingDataSourceMutableLiveData;
    private GetMoviesDataService getMoviesDataService;

    public MovieDataSourceFactory(GetMoviesDataService getMoviesDataService) {
        this.getMoviesDataService = getMoviesDataService;
        movingDataSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource<Integer, Result> create() {
        movingDataSource = new MovingDataSource(getMoviesDataService);
        movingDataSourceMutableLiveData.postValue(movingDataSource);
        return movingDataSource;
    }

    public MutableLiveData<MovingDataSource> getMovingDataSourceMutableLiveData() {
        return movingDataSourceMutableLiveData;
    }
}
