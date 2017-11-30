package com.sooch.mvvm.api.service

import com.sooch.mvvm.model.Repo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Takashi Sou on 2017/11/24.
 */
interface GitHubService {
    /**
     * List public repositories for the specified user.
     *
     * <a href="https://developer.github.com/v3/repos/#list-user-repositories">List user repositories</a>
     *
     * @param user
     *
     * @return
     */
    @GET("users/{user}/repos")
    fun repos(@Path("user") user: String): Single<List<Repo>>
}