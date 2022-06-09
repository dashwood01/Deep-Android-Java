package com.dashwood.neweducation.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.dashwood.neweducation.db.BookDAO;
import com.dashwood.neweducation.db.CategoryDAO;
import com.dashwood.neweducation.db.DashwoodAppDatabase;
import com.dashwood.neweducation.db.DashwoodDAO;
import com.dashwood.neweducation.service.GetMoviesDataService;
import com.dashwood.neweducation.service.RetrofitInstance;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    RetrofitInstance providerRetrofit() {
        return new RetrofitInstance();
    }
}
