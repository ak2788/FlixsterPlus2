package com.example.flixsterplus2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class MovieAdapter(
    private val context: Context,
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPoster: ImageView = view.findViewById(R.id.ivPoster)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_media, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.tvTitle.text = movie.title

        val posterUrl = "https://image.tmdb.org/t/p/w342${movie.posterPath}"
        Glide.with(context)
            .load(posterUrl)
            .apply(RequestOptions().transform(RoundedCorners(20)))
            .into(holder.ivPoster)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("title", movie.title)
                putExtra("overview", movie.overview)
                putExtra("backdrop_path", movie.backdropPath)
                putExtra("vote_average", movie.voteAverage)
                putExtra("release_date", movie.releaseDate)
                putExtra("language", movie.originalLanguage)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = movies.size
}