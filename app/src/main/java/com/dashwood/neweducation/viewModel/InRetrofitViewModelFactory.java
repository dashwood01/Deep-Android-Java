package com.dashwood.neweducation.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dashwood.neweducation.service.GetMoviesDataService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InRetrofitViewModelFactory implements ViewModelProvider.Factory {
    private final GetMoviesDataService getMoviesDataService;

    @Inject
    public InRetrofitViewModelFactory(GetMoviesDataService getMoviesDataService) {
        this.getMoviesDataService = getMoviesDataService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return null;
    }
}
