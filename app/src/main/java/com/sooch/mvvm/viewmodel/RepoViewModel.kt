package com.sooch.mvvm.viewmodel

import android.arch.lifecycle.*
import com.sooch.mvvm.model.Repo
import com.sooch.mvvm.repository.RepoRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


/**
 * Created by Takashi Sou on 2017/11/13.
 */
class RepoViewModel @Inject constructor(private val repository: RepoRepository) : ViewModel() {

    private val owner = MutableLiveData<String>()
    private val repos: LiveData<List<Repo>>

    init {
        // Transformations#switchMapは, 第1引数のLiveDataオブジェクトに対し, 第2引数の関数を適用し, その結果を下流に伝達します.
        // https://developer.android.com/topic/libraries/architecture/livedata.html?#transform_livedata
        repos = Transformations.switchMap(this.owner) {
            if (it.isEmpty()) {
                AbsentLiveData.create<List<Repo>>()
            } else {
                LiveDataReactiveStreams.fromPublisher(repository.loadRepos(it)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toFlowable()
                        .onErrorResumeNext(Flowable.empty())
                )
            }
        }
    }

    fun get(): LiveData<List<Repo>> {
        return repos
    }

    fun setOwner(owner: String) {
        if (Objects.equals(this.owner.value, owner)) {
            return
        }
        this.owner.value = owner
    }
}