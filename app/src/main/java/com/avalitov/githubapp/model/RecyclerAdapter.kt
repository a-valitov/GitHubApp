package com.avalitov.githubapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.githubapp.R

class RecyclerAdapter(private val repos: ArrayList<Repository>)
    : RecyclerView.Adapter<RecyclerAdapter.RepoViewHolder>() {


    class RepoViewHolder(itemView : View, repos: ArrayList<Repository>) : RecyclerView.ViewHolder(itemView){
        var mainLayout : LinearLayout = itemView.findViewById(R.id.ll_repos)
        var expandableLayout : LinearLayout = itemView.findViewById(R.id.ll_expandable)
        var nameTextView: TextView = itemView.findViewById(R.id.tv_name)
        var descriptionTextView: TextView? = null

        init {
            descriptionTextView = itemView.findViewById(R.id.tv_description)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return RepoViewHolder(itemView, repos)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.nameTextView?.text = repos[position].name
        holder.descriptionTextView?.text = repos[position].owner.login

        val isExpanded : Boolean = repos[position].expanded
        holder.expandableLayout.visibility = if (isExpanded) View.VISIBLE else View.GONE

        holder.mainLayout.setOnClickListener {
            var repo : Repository = repos[position]
            repo.expanded = !repo.expanded
            notifyItemChanged(position)
        }


    }

    override fun getItemCount(): Int {
        return repos.size
    }

}