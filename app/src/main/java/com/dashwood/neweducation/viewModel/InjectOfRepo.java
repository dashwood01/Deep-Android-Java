package com.dashwood.neweducation.viewModel;

import com.dashwood.neweducation.service.GetMoviesDataService;

import javax.inject.Inject;

public class InjectOfRepo {

    private GetMoviesDataService getMoviesDataService;

    @Inject
    public InjectOfRepo(GetMoviesDataService getMoviesDataService){
        this.getMoviesDataService = getMoviesDataService;
    }

    public GetMoviesDataService getGetMoviesDataService() {
        return getMoviesDataService;
    }
}
