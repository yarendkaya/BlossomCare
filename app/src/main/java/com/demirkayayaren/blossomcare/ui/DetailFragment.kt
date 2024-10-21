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
        initArgs()
        initViews()

    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        initArgs()
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initViews()
//    }

    private fun initViews() {
        // Safely initialize views with blossom data, if available
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

    private fun initArgs() {
        this.blossom = arguments?.serializable<Blossom>("blossom")
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}