package com.demirkayayaren.blossomcare.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.demirkayayaren.blossomcare.data.model.BlossomFav
import com.demirkayayaren.blossomcare.databinding.ItemFavBlossomBinding
import com.demirkayayaren.blossomcare.ui.BlossomViewModel
import javax.inject.Inject

class FavoritesAdapter@Inject constructor(private val viewModel: BlossomViewModel) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    private var favoritesList: MutableList<BlossomFav> = mutableListOf()

    inner class FavoritesViewHolder(private val binding: ItemFavBlossomBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(blossom: BlossomFav) {
            binding.tvFavCommonName.text = blossom.commonName
            binding.ivFavBlossomThumbNail.load(blossom.thumbNail)
            binding.removeItem.setOnClickListener {
                deleteFavorite(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            ItemFavBlossomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val blossom = favoritesList[position]
        holder.bind(blossom)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: MutableList<BlossomFav>) {
        favoritesList.clear()
        favoritesList.addAll(list)
        notifyDataSetChanged()
    }

    private fun deleteFavorite(position: Int) {
        val blossomToDelete = favoritesList[position]
        viewModel.deleteBlossom(blossomToDelete)
        favoritesList.removeAt(position)
        notifyItemRemoved(position)
    }
}