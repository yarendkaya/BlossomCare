package com.demirkayayaren.blossomcare.ui

import android.util.Log
import android.widget.Toast
import coil3.load
import com.demirkayayaren.blossomcare.data.model.Blossom
import com.demirkayayaren.blossomcare.data.model.BlossomFav
import com.demirkayayaren.blossomcare.databinding.FragmentDetailBinding
import com.demirkayayaren.blossomcare.ui.base.BaseFragment
import com.demirkayayaren.blossomcare.util.serializable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    override lateinit var viewModel: BlossomViewModel

    private var blossom: Blossom? = null
    private var blossomFav: BlossomFav? = null

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

        binding.fab.setOnClickListener {
            blossom?.let {
                blossomFav = it.convertToBlossomFav()
                blossomFav?.let {
                    viewModel.saveBlossom(blossomFav!!)
                    Toast.makeText(requireContext(), "Favorilere eklendi", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun initArgs() {
        arguments?.let {
            blossom = it.serializable("blossom") as? Blossom
        }
    }
}
