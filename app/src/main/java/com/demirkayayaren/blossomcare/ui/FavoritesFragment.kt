package com.demirkayayaren.blossomcare.ui

import com.demirkayayaren.blossomcare.adapter.FavoritesAdapter
import com.demirkayayaren.blossomcare.databinding.FragmentFavoritesBinding
import com.demirkayayaren.blossomcare.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {
    override lateinit var viewModel: BlossomViewModel

    override fun setupUI() {
        super.setupUI()
        viewModel.getAllSavedBlossoms()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.favoriteBlossoms.observe(viewLifecycleOwner) { favorites ->
            val adapter = FavoritesAdapter()
            adapter.setData(favorites.toMutableList())
            binding.rvFavorites.adapter = adapter
        }
    }
}