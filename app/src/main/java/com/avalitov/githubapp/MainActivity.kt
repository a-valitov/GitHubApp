package com.avalitov.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avalitov.githubapp.model.RecyclerAdapter
import com.avalitov.githubapp.model.Repository
import com.avalitov.githubapp.model.User
import com.avalitov.githubapp.responses.SearchRepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var tvText : TextView
    lateinit var btnSearch : ImageView
    lateinit var etSearch : EditText

    var reposArrayList = arrayListOf<Repository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvText = findViewById(R.id.tv_text)
        btnSearch = findViewById(R.id.btn_search)
        etSearch = findViewById(R.id.et_search)

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString()
            if (query.isNotEmpty()) {
                getRepositoriesFromSearch(query)
            }
        }

    }

    private fun getUsers() {
        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getUsers()
        retrofitData.enqueue(object : Callback<List<User>?> {
            override fun onResponse(call: Call<List<User>?>, response: Response<List<User>?>) {
                val responseBody = response.body()

                if (responseBody != null) {
                    val userStringBuilder = StringBuilder()
                    for (dataUnit in responseBody) {
                        userStringBuilder.append(dataUnit.login)
                        userStringBuilder.append("\n")
                    }

                    tvText.text = userStringBuilder
                }
            }

            override fun onFailure(call: Call<List<User>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }

    private fun getRepositoriesFromSearch(query : String){

        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getRepositories(query)
        retrofitData.enqueue(object : Callback<SearchRepoResponse?> {
            override fun onResponse(call: Call<SearchRepoResponse?>, response: Response<SearchRepoResponse?>) {
                val responseBody = response.body()?.items

                if (responseBody != null) {
                    reposArrayList = responseBody
                    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = RecyclerAdapter(reposArrayList)
                } else {
                    Toast.makeText(this@MainActivity, "Repositories not found.", Toast.LENGTH_SHORT)
                }
            }

            override fun onFailure(call: Call<SearchRepoResponse?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }
        })
    }


    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}