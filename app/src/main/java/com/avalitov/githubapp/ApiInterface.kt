package com.avalitov.githubapp

import com.avalitov.githubapp.model.User
import com.avalitov.githubapp.responses.SearchRepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    //Call is a Retrofit method that sends a request to a webserver and returns a response

    @GET("users")
    fun getUsers() : Call<List<User>>

    @GET("search/repositories?q=SevenMinuteWorkout")
    //@Headers("Authorization: token TOKEN_HERE")
    fun getRepositories() : Call<SearchRepoResponse>

}