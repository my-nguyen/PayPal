package com.nguyen.paypal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.nguyen.paypal.databinding.FragmentSimilarBinding

private const val TAG = "Truong-SimilarFragment"
class SimilarFragment : Fragment(R.layout.fragment_similar) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies = mutableListOf<Movie>()
        val adapter = SimilarAdapter(movies)
        FragmentSimilarBinding.bind(view).similar.adapter = adapter

        val args: SimilarFragmentArgs by navArgs()
        val viewModel : SimilarViewModel by viewModels()
        viewModel.getSimilar(args.movieId)
        viewModel.movies.observe(requireActivity()) {
            movies.clear()
            movies.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }
}