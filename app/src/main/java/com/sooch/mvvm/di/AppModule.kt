package com.sooch.mvvm.di

import android.app.Application
import android.content.Context
import com.sooch.mvvm.api.service.GitHubService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Takashi Sou on 2017/11/14.
 */
@Module(includes = arrayOf(ViewModelModule::class))
//@Module
class AppModule {
    @Provides
    fun provideContext(app : Application): Context = app.applicationContext

    /**
     * Provides [OkHttpClient]
     *
     * @return [OkHttpClient]
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }

    /**
     * Provides [GitHubService]
     *
     * @return [GitHubService]
     */
    @Provides
    @Singleton
    fun provideGitHubService(client: OkHttpClient): GitHubService {
        return Retrofit.Builder().client(client)
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(GitHubService::class.java)
    }
}