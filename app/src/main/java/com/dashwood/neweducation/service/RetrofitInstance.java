package com.dashwood.neweducation.service;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";

    @Inject
    public RetrofitInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public GetMoviesDataService getService() {
        return retrofit.create(GetMoviesDataService.class);
    }
}
