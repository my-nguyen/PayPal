package com.nguyen.paypal

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.nguyen.paypal.databinding.FragmentDetailBinding

private const val TAG = "Truong-DetailFragment"
class DetailFragment : Fragment(R.layout.fragment_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: MainViewModel by activityViewModels()
        viewModel.movies.observe(requireActivity()) { movies ->
            val args: DetailFragmentArgs by navArgs()
            movies.find { it.id == args.movieId }?.let { movie ->
                FragmentDetailBinding.bind(view).apply {
                    title.text = movie.title
                    voteAverage.text = movie.vote_average.toString()
                    overview.text = movie.overview
                    Glide.with(requireView()).load(movie.poster()).into(poster)

                    viewSimilar.visibility = View.VISIBLE
                    viewSimilar.setOnClickListener {
                        Log.d(TAG, "movie: $movie")
                        val action = DetailFragmentDirections.toSimilarFragment(movie.id)
                        findNavController().navigate(action)
                    }

                    markFavorite.visibility = View.VISIBLE
                    markFavorite.setOnClickListener {
                        viewModel.addFavorite(movie)
                    }
                }
            }
        }
    }
}