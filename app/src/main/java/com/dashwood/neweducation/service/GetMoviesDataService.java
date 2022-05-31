package com.dashwood.neweducation.service;


import com.dashwood.neweducation.db.Dashwood;
import com.dashwood.neweducation.inf.Movie;
import com.dashwood.neweducation.inf.User;

import java.util.List;

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
