package com.sooch.mvvm.view.repos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.sooch.mvvm.R
import com.sooch.mvvm.model.Repo

/**
 * Created by Takashi Sou on 2017/11/24.
 */
class RepoAdapter internal constructor(private val context: Context, private val repos: List<Repo>) : BaseAdapter() {

    private val inflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val v: View
        val item = getItem(position) as Repo

        if (convertView == null) {
            v = inflater.inflate(R.layout.list_item, parent, false)
            v.findViewById<TextView>(R.id.name).text = item.name
            v.findViewById<TextView>(R.id.description).text = item.description
        } else {
            v = convertView
        }

        return v
    }

    override fun getItem(position: Int): Any = repos[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = repos.size
}
