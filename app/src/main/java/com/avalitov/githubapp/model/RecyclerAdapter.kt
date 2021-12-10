package com.avalitov.githubapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.githubapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

// new since Glide v4
@GlideModule
class MyAppGlideModule : AppGlideModule() {
    // leave empty for now
}

class RecyclerAdapter(private val repos: ArrayList<Repository>)
    : RecyclerView.Adapter<RecyclerAdapter.RepoViewHolder>() {


    class RepoViewHolder(itemView : View, repos: ArrayList<Repository>) : RecyclerView.ViewHolder(itemView){
        var mainLayout : LinearLayout = itemView.findViewById(R.id.ll_repos)
        var expandableLayout : LinearLayout = itemView.findViewById(R.id.ll_expandable)
        var nameTextView: TextView = itemView.findViewById(R.id.tv_name)
        var descriptionTextView : TextView = itemView.findViewById(R.id.tv_description)
        var ownerTextView : TextView = itemView.findViewById(R.id.tv_owner)
        var forksTextView : TextView = itemView.findViewById(R.id.tv_forks_count)
        var starsTextView : TextView = itemView.findViewById(R.id.tv_stars_count)
        var createdAtTextView : TextView = itemView.findViewById(R.id.tv_created_at)
        var avatarImageView : ImageView = itemView.findViewById(R.id.iv_avatar)

        //init {}

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return RepoViewHolder(itemView, repos)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.nameTextView?.text = repos[position].name
        holder.ownerTextView?.text = "Author: ${repos[position].owner.login}"
        holder.descriptionTextView?.text = repos[position].description
        holder.forksTextView?.text = repos[position].forks_count.toString()
        holder.starsTextView?.text = repos[position].stargazers_count.toString()
        holder.createdAtTextView?.text = repos[position].created_at

        Glide.with(holder.avatarImageView)
                .load(repos[position].owner.avatar_url)
                .centerCrop()
                .into(holder.avatarImageView);

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