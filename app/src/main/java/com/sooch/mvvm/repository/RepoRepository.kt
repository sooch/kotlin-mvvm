package com.sooch.mvvm.repository

import com.sooch.mvvm.api.service.GitHubService
import javax.inject.Inject

/**
 * Created by Takashi Sou on 2017/11/16.
 */
class RepoRepository @Inject constructor(private val service: GitHubService) {
    /**
     * List repositories
     *
     * @param user
     */
    fun loadRepos(user: String) = service.repos(user)
}