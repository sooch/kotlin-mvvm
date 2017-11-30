package com.sooch.mvvm.view.repos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.SearchView
import com.sooch.mvvm.R
import com.sooch.mvvm.databinding.ActivityReposBinding
import com.sooch.mvvm.model.Repo
import com.sooch.mvvm.viewmodel.RepoViewModel
import com.sooch.mvvm.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_repos.*
import javax.inject.Inject


/**
 * Created by Takashi Sou on 2017/11/12.
 */
class RepoActivity : DaggerAppCompatActivity(), RepoView {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(RepoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityReposBinding>(this, R.layout.activity_repos)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.inflateMenu(R.menu.search)

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                viewModel.setOwner(s)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })

        // Observe LiveData
        viewModel.get().observe(this, Observer { t ->
            if (t != null) {
                renderView(t)
            }
        })

        // Fire
        viewModel.setOwner("google")
    }

    override fun renderView(repos: List<Repo>) {
        list.adapter = RepoAdapter(this, repos)
    }

    // TODO: catch throwable on LiveData
    override fun showError(e: Throwable) {
        Snackbar.make(list, e.message.toString(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
    }
}
