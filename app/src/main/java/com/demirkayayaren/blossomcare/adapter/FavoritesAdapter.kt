package com.demirkayayaren.blossomcare.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.demirkayayaren.blossomcare.data.model.BlossomFav
import com.demirkayayaren.blossomcare.databinding.ItemBlossomBinding

class FavoritesAdapter() :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    private var favoritesList: MutableList<BlossomFav> = mutableListOf()

    inner class FavoritesViewHolder(private val binding: ItemBlossomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(blossom: BlossomFav) {
            binding.tvCommonName.text = blossom.commonName
            binding.ivBlossomThumbNail.load(blossom.thumbNail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {

        return FavoritesViewHolder(
            ItemBlossomBinding.inflate(
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
}