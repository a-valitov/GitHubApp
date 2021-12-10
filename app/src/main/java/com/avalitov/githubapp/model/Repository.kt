package com.avalitov.githubapp.model

data class Repository(
    val id : String,
    val name : String,
    val owner : User,
    //TODO : Serialized names
    val description : String,
    val forks_count : Int,
    val stargazers_count : Int,
    val created_at : String,

    var expanded : Boolean
)

