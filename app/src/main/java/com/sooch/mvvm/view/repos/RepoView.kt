package com.sooch.mvvm.view.repos

import com.sooch.mvvm.model.Repo

/**
 * Created by Takashi Sou on 2017/11/29.
 */
interface RepoView {
    /**
     * Render the view.
     */
    fun renderView(repos: List<Repo>)

    /**
     * Called when fetch of data source failure.
     */
    fun showError(e: Throwable)
}