package com.nguyen.paypal

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyen.paypal.databinding.FragmentSimilarBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "Truong-SimilarFragment"
class SimilarFragment : Fragment(R.layout.fragment_similar) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies = mutableListOf<Movie>()
        val similarAdapter = SimilarAdapter(movies)
        FragmentSimilarBinding.bind(view).recycler.apply {
            adapter = similarAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        }

        val args: SimilarFragmentArgs by navArgs()
        val viewModel : SimilarViewModel by viewModels()
        Network.service.getSimilar2(args.movieId, "1fca74d1a066b2433a06dea9b96239fe").enqueue(object: Callback<Movies> {
            override fun onResponse(p0: Call<Movies>, p1: Response<Movies>) {
                Log.d(TAG, "onResponse $p1")
            }

            override fun onFailure(p0: Call<Movies>, p1: Throwable) {
                Log.d(TAG, "onFailure $p1")
            }

        })
        viewModel.getSimilar(args.movieId)
        viewModel.movies.observe(requireActivity()) {
            movies.clear()
            movies.addAll(it)
            similarAdapter.notifyDataSetChanged()
        }
    }
}