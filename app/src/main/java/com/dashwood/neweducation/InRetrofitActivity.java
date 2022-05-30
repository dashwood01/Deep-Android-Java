package com.dashwood.neweducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.dashwood.neweducation.adapter.AdapterRecItemMovie;
import com.dashwood.neweducation.inf.Movie;
import com.dashwood.neweducation.service.GetMoviesDataService;
import com.dashwood.neweducation.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InRetrofitActivity extends AppCompatActivity {

    private AdapterRecItemMovie adapterRecItemUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_retrofit);
        RecyclerView rec = findViewById(R.id.recItemUser);
        rec.setLayoutManager(new GridLayoutManager(this, 2));
        adapterRecItemUser = new AdapterRecItemMovie();
        rec.setAdapter(adapterRecItemUser);
        connectServerGetUsers();
    }

    private void connectServerGetUsers() {
       /* GetMoviesDataService getUsersDataService = RetrofitInstance.getService();
        Call<List<User>> result = getUsersDataService.getResult();
        result.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                List<User> user = response.body();
                if (user == null)
                    return;
                adapterRecItemUser.sendItems(user);
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                t.printStackTrace();

            }
        });*/
        GetMoviesDataService getMoviesDataService = RetrofitInstance.getService();
        Call<Movie> result = getMoviesDataService.getMovie("d87230560436d18e59f8682ab0e85e34");
        result.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.body() == null)
                    return;
                if (response.body().getResults() == null)
                    return;
                adapterRecItemUser.sendItems(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}