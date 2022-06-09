package com.dashwood.neweducation.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.dashwood.neweducation.inf.Movie;
import com.dashwood.neweducation.model.Book;
import com.dashwood.neweducation.model.Category;
import com.dashwood.neweducation.model.DashwoodRepository;
import com.dashwood.neweducation.service.GetMoviesDataService;

import java.util.List;

public class InRetrofitViewModel extends ViewModel {

    private LiveData<List<Movie>> listLiveData;
    private GetMoviesDataService getMoviesDataService;

}
