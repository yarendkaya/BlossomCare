package com.demirkayayaren.blossomcare.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil3.load
import com.demirkayayaren.blossomcare.data.model.Blossom
import com.demirkayayaren.blossomcare.databinding.FragmentDetailBinding
import com.demirkayayaren.blossomcare.util.serializable


class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var blossom: Blossom? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        blossom?.let {
            binding.tvCommonName.text = it.commonName
            binding.tvCycle.text = it.cycle
            binding.tvOtherName.text = it.otherName.toString()
            binding.tvScientificName.text = it.scientificName.toString()
            binding.tvSunlight.text = it.sunlight.toString()
            binding.tvWatering.text = it.watering
            binding.ivBlossom.load(it.defaultImage.originalUrl)

        }
    }

    fun initArgs() {
        this.blossom = arguments?.serializable<Blossom>("blossom")
    }

}