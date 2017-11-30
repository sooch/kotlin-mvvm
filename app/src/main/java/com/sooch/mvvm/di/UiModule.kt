package com.sooch.mvvm.di

import android.arch.lifecycle.ViewModelProvider
import com.sooch.mvvm.view.repos.RepoActivity
import com.sooch.mvvm.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Takashi Sou on 2017/11/13.
 */
@Module
abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = arrayOf())
    abstract fun contributeReposActivity(): RepoActivity
}