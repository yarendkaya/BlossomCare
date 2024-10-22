package com.demirkayayaren.blossomcare.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.demirkayayaren.blossomcare.R
import com.demirkayayaren.blossomcare.adapter.BlossomAdapter
import com.demirkayayaren.blossomcare.data.model.Blossom
import com.demirkayayaren.blossomcare.data.network.NetworkResult
import com.demirkayayaren.blossomcare.databinding.FragmentDashboardBinding
import com.demirkayayaren.blossomcare.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    override lateinit var viewModel: BlossomViewModel


    override fun setupUI() {
        super.setupUI()
        viewModel.fetchResult()
        observeBlossom()
        setAdapter(viewModel.blossomResponse.value?.data?.blossomList)// niye yaptım bilmiyorum (chat gpt)
    }

    private fun observeBlossom() {
        viewModel.blossomResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    setAdapter(response.data?.blossomList)
                    Log.e("BlossomFragment", "Success")
                }

                is NetworkResult.Error -> {
                    Log.e("BlossomFragment", "Error: ${response.message}")
                    handleErrorResponse()
                }

                is NetworkResult.Loading -> {
                    Log.e("BlossomFragment", "Loading")
                }
            }
        }
    }

    private fun handleErrorResponse() {
        binding.imageViewError.visibility = View.VISIBLE
    }

    private fun setAdapter(data: List<Blossom>?) {
        data?.let {
            val adapter = BlossomAdapter { position ->
                val bundle = Bundle().apply {
                    putSerializable("blossom", it[position])
                }
                findNavController().navigate(
                    R.id.action_dashboardFragment_to_detailFragment,
                    bundle
                )
            }
            adapter.setData(it.toMutableList())
            binding.rvBlossomResult.adapter = adapter
        }
    }
}