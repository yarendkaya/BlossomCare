package com.demirkayayaren.blossomcare.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.demirkayayaren.blossomcare.adapter.BlossomAdapter
import com.demirkayayaren.blossomcare.data.model.Blossom
import com.demirkayayaren.blossomcare.data.network.NetworkResult
import com.demirkayayaren.blossomcare.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val viewModel: BlossomViewModel by viewModels()

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchResult()
        observeBlossom()

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
                }

                is NetworkResult.Loading -> {
                    Log.e("BlossomFragment", "Loading")
                }
            }
        }
    }

    private fun setAdapter(data: List<Blossom>?) {
        data?.let {
            val adapter = BlossomAdapter()
            adapter.setData(it.toMutableList())
            binding.rvBlossomResult.adapter = adapter
        }
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null // Avoid memory leaks with view binding
//    }

}