package com.dashwood.neweducation.extra;

import android.app.Application;

import com.dashwood.neweducation.di.ApplicationModule;
import com.dashwood.neweducation.di.DaggerMovieComponent;
import com.dashwood.neweducation.di.MovieComponent;
import com.dashwood.neweducation.di.RepositoryModule;

public class A extends Application {
    private static A applicationClass;
    private MovieComponent movieComponent;

    public static A getApplicationClass() {
        return applicationClass;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationClass = this;
       /* bookComponent = DaggerBookComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .repositoryDatabase(new RepositoryDatabase()).build();*/
        movieComponent = DaggerMovieComponent.create();
    }

    public MovieComponent getMovieComponent() {
        return movieComponent;
    }
}
