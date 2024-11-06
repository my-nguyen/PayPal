package com.nguyen.paypal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.nguyen.paypal.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)
        val fragments = listOf(NowPlayingFragment(), FavoritesFragment())
        binding.viewpager.adapter = TabsAdapter(fragments, childFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabs, binding.viewpager) { tab, position ->
            tab.text = if (position == 0) "Now Showing" else "Favorites"
        }.attach()
    }
}