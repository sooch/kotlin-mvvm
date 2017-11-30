package com.sooch.mvvm.viewmodel

import android.arch.lifecycle.LiveData


/**
 * Created by Takashi Sou on 2017/11/13.
 */
class AbsentLiveData<T> private constructor() : LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}