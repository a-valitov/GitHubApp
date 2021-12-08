package com.avalitov.githubapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.githubapp.R

class RecyclerAdapter(private val repos: ArrayList<Repository>)
    : RecyclerView.Adapter<RecyclerAdapter.RepoViewHolder>() {

    class RepoViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var nameTextView: TextView? = null
        var descriptionTextView: TextView? = null

        init {
            nameTextView = itemView.findViewById(R.id.tv_name)
            descriptionTextView = itemView.findViewById(R.id.tv_description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return RepoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.nameTextView?.text = repos[position].name
        holder.descriptionTextView?.text = repos[position].owner.login
    }

    override fun getItemCount(): Int {
        return repos.size
    }

}