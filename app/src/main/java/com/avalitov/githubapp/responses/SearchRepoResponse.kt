package com.avalitov.githubapp.responses

import com.avalitov.githubapp.model.Repository

data class SearchRepoResponse(
    val items : ArrayList<Repository>
)
