package com.dashwood.neweducation.di;

import android.app.Application;

import com.dashwood.neweducation.model.DashwoodRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryDatabase {
    @Singleton
    @Provides
    DashwoodRepository providerRepository(Application application) {
        return new DashwoodRepository(application);
    }
}
