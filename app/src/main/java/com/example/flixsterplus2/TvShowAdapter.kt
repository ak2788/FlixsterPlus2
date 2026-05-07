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

class TvShowAdapter(
    private val context: Context,
    private val shows: List<TvShow>
) : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPoster: ImageView = view.findViewById(R.id.ivPoster)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_media, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = shows[position]
        holder.tvTitle.text = show.name

        val posterUrl = "https://image.tmdb.org/t/p/w342${show.posterPath}"
        Glide.with(context)
            .load(posterUrl)
            .apply(RequestOptions().transform(RoundedCorners(20)))
            .into(holder.ivPoster)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("title", show.name)
                putExtra("overview", show.overview)
                putExtra("backdrop_path", show.backdropPath)
                putExtra("vote_average", show.voteAverage)
                putExtra("release_date", show.firstAirDate)
                putExtra("language", show.originalLanguage)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = shows.size
}