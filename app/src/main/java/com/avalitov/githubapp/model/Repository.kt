package com.avalitov.githubapp.model

data class Repository(
    val id : String,
    val name : String,
    val owner : User,
    val description : String,

    var expanded : Boolean
)

