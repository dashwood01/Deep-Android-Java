package com.dashwood.neweducation.service;


import com.dashwood.neweducation.db.Dashwood;
import com.dashwood.neweducation.inf.Movie;
import com.dashwood.neweducation.inf.User;
import com.google.android.datatransport.runtime.retries.Function;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GetMoviesDataService {
    @GET("users")
    Call<List<User>> getResult();

    @GET("movie/popular")
    Call<Movie> getMovie(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<Movie> getMoviePaging(@Query("api_key") String apiKey, @Query("page") int page);
}
