package com.dashwood.neweducation.di;


import com.dashwood.neweducation.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/*@Singleton
@Component(modules = {ApplicationModule.class, RepositoryDatabase.class})*/
public interface BookComponent {
    void inject(MainActivity mainActivity);
}
