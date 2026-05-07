package com.example.flixsterplus2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra("title") ?: ""
        val overview = intent.getStringExtra("overview") ?: ""
        val backdropPath = intent.getStringExtra("backdrop_path")
        val voteAverage = intent.getDoubleExtra("vote_average", 0.0)
        val releaseDate = intent.getStringExtra("release_date") ?: ""
        val language = intent.getStringExtra("language") ?: ""

        findViewById<TextView>(R.id.tvDetailTitle).text = title
        findViewById<TextView>(R.id.tvOverview).text = overview
        findViewById<TextView>(R.id.tvRating).text = "⭐ Rating: ${"%.1f".format(voteAverage)}/10"
        findViewById<TextView>(R.id.tvDate).text = "📅 Release Date: $releaseDate"
        findViewById<TextView>(R.id.tvLanguage).text = "🌐 Language: ${language.uppercase()}"

        val backdropUrl = "https://image.tmdb.org/t/p/w780$backdropPath"
        Glide.with(this)
            .load(backdropUrl)
            .into(findViewById(R.id.ivBackdrop))
    }
}