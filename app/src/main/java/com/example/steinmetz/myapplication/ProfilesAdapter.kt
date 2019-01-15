package com.example.steinmetz.myapplication

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.jetbrains.anko.padding

class ProfilesAdapter: BaseAdapter {

    private var profiles = listOf<Profile>()
    private var context: Context? = null

    constructor(context: Context, profiles: List<Profile>) : super() {
        this.profiles = profiles
        this.context = context
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = TextView(context)
        view.padding = 8
        view.text = profiles[position].username
        return view
    }

    override fun getItem(position: Int): Any {
        return profiles[position]
    }

    override fun getItemId(position: Int): Long {
        return profiles[0].id.toLong()
    }

    override fun getCount(): Int {
        return profiles.size
    }

}