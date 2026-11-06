package com.nguyen.paypal

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyen.paypal.databinding.FragmentNowPlayingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "NowPlayingFragment"
class NowPlayingFragment : Fragment(R.layout.fragment_now_playing) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies = mutableListOf<Movie>()
        val moviesAdapter = MoviesAdapter(movies, object : MoviesAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                val action = MainFragmentDirections.toDetailFragment(movies[position].id)
                findNavController().navigate(action)
            }
        })
        FragmentNowPlayingBinding.bind(view).nowPlaying.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

        /*val viewModel: MainViewModel by activityViewModels()
        viewModel.movies.observe(requireActivity()) {
            movies.clear()
            movies.addAll(it)
            moviesAdapter.notifyDataSetChanged()
        }*/

        Network.getNowPlaying2().enqueue(object : Callback<Movies> {
            override fun onResponse(p0: Call<Movies>, p1: Response<Movies>) {
                p1.body()?.results?.let {
                    movies.clear()
                    movies.addAll(it)
                    moviesAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(p0: Call<Movies>, p1: Throwable) {
                Log.d(TAG, "onFailure $p1")
            }
        })
    }
}