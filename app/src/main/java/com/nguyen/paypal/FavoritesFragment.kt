package com.nguyen.paypal

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyen.paypal.databinding.FragmentFavoritesBinding

private const val TAG = "Truong-FavoritesFragment"
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies = mutableListOf<Movie>()
        val moviesAdapter = MoviesAdapter(movies, object : MoviesAdapter.OnClickListener {
            override fun onItemClick(position: Int) {
                val action = MainFragmentDirections.toDetailFragment(movies[position].id)
                findNavController().navigate(action)
            }
        })
        FragmentFavoritesBinding.bind(view).favorites.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

        val viewModel: MainViewModel by activityViewModels()
        viewModel.favorites.observe(requireActivity()) {
            movies.clear()
            Log.d(TAG, "preparing to display favorites: $it")
            movies.addAll(it)
            moviesAdapter.notifyDataSetChanged()
        }
    }
}