package com.sooch.mvvm


/**
 * Created by Takashi Sou on 2017/11/29.
 */
class Resource<T> constructor(val status: Status, val data: T?, val error: Throwable?) {

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable): Resource<T> {
            return Resource(Status.ERROR, null, error)
        }
    }
}