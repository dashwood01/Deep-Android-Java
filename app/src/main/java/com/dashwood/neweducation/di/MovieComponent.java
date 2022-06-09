package com.dashwood.neweducation.di;


import com.dashwood.neweducation.InRetrofitActivity;
import com.dashwood.neweducation.MainActivity;
import com.dashwood.neweducation.service.GetMoviesDataService;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RepositoryModule.class)
public interface MovieComponent {
    void inject(InRetrofitActivity retrofitActivity);
}
