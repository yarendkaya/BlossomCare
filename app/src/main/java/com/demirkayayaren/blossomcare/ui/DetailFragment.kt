package com.demirkayayaren.blossomcare.ui

import android.util.Log
import coil3.load
import com.demirkayayaren.blossomcare.data.model.Blossom
import com.demirkayayaren.blossomcare.databinding.FragmentDetailBinding
import com.demirkayayaren.blossomcare.ui.base.BaseFragment
import com.demirkayayaren.blossomcare.util.serializable


class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    override lateinit var viewModel: BlossomViewModel

    private var blossom: Blossom? = null

    override fun setupUI() {
        super.setupUI()
        blossom?.let {
            with(binding) {
                tvCommonName.text = it.commonName
                tvCycle.text = it.cycle
                tvOtherName.text = it.otherName.toString()
                tvScientificName.text = it.scientificName.toString()
                tvSunlight.text = it.sunlight.toString()
                tvWatering.text = it.watering
                ivBlossom.load(it.defaultImage.originalUrl)
            }
        } ?: run {
            Log.e("DetailFragment", "No Blossom data available")
        }
    }

    override fun initArgs() {
        arguments?.let {
            blossom = it.serializable("blossom") as? Blossom
        }
    }
}
