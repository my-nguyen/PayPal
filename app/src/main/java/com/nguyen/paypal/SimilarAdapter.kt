package com.nguyen.paypal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nguyen.paypal.databinding.FragmentDetailBinding

class SimilarAdapter(val movies: List<Movie>) : RecyclerView.Adapter<SimilarAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: FragmentDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                title.text = movie.title
                voteAverage.text = movie.vote_average.toString()
                overview.text = movie.overview
                Glide.with(itemView).load(movie.poster()).into(poster)
                viewSimilar.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentDetailBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}