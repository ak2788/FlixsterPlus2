package com.example.flixsterplus2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
    private lateinit var rvMovies: RecyclerView
    private lateinit var rvTvShows: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMovies = findViewById(R.id.rvMovies)
        rvTvShows = findViewById(R.id.rvTvShows)

        rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvTvShows.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        fetchMovies()
        fetchTvShows()
    }

    private fun fetchMovies() {
        val client = AsyncHttpClient()
        val url = "https://api.themoviedb.org/3/movie/popular?api_key=$API_KEY"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val results = json.jsonObject.getJSONArray("results").toString()
                val type = object : TypeToken<List<Movie>>() {}.type
                val movies: List<Movie> = Gson().fromJson(results, type)

                rvMovies.adapter = MovieAdapter(this@MainActivity, movies)
            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                // Handle error - log or show a toast
            }
        })
    }

    private fun fetchTvShows() {
        val client = AsyncHttpClient()
        val url = "https://api.themoviedb.org/3/tv/popular?api_key=$API_KEY"

        client.get(url, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val results = json.jsonObject.getJSONArray("results").toString()
                val type = object : TypeToken<List<TvShow>>() {}.type
                val shows: List<TvShow> = Gson().fromJson(results, type)

                rvTvShows.adapter = TvShowAdapter(this@MainActivity, shows)
            }

            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                // Handle error
            }
        })
    }
}