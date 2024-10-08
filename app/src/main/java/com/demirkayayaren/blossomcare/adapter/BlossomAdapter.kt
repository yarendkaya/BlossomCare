package com.demirkayayaren.blossomcare.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demirkayayaren.blossomcare.data.model.Blossom
import com.demirkayayaren.blossomcare.databinding.ItemBlossomBinding

class BlossomAdapter() :
    RecyclerView.Adapter<BlossomAdapter.BlossomViewHolder>() {

    private var blossomList: MutableList<Blossom> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlossomViewHolder {
        return BlossomViewHolder(
            ItemBlossomBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return blossomList.size
    }

    override fun onBindViewHolder(holder: BlossomViewHolder, position: Int) {
        val blossom = blossomList[position]
        holder.bind(blossom)
    }

    inner class BlossomViewHolder(private val binding: ItemBlossomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(blossom: Blossom) {
            binding.tvCommonName.text = blossom.commonName
            binding.tvSpecificName.text = blossom.scientificName.toString()
            binding.cycle.text = blossom.cycle
            binding.watering.text = blossom.watering
            binding.sunlight.text = blossom.sunlight.toString()
            binding.otherName.text = blossom.otherName.toString()
        }
    }

    fun setData(list: MutableList<Blossom>) {
        blossomList.clear()
        blossomList.addAll(list)
        notifyDataSetChanged()
    }

}