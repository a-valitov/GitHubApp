package com.avalitov.githubapp

import com.avalitov.githubapp.model.User
import com.avalitov.githubapp.responses.SearchRepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    //Call is a Retrofit method that sends a request to a webserver and returns a response

    @GET("users")
    fun getUsers() : Call<List<User>>

    @GET("search/repositories")
    //TODO: Put token safely (to Secrets.kt or something)
    //@Headers("Authorization: token TOKEN_HERE")
    fun getRepositories(
        @Query("q") query: String
    ) : Call<SearchRepoResponse>

}