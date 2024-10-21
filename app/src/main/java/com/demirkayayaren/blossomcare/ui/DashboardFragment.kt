package com.demirkayayaren.blossomcare.ui

import android.os.Bundle
import android.util.Log
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

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment directly
//        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false)
//
//        // Initialize RecyclerView
//        rvBlossomResult = rootView.findViewById(R.id.rvBlossomResult)
//
//        return rootView
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel.fetchResult()
//        observeBlossom()
//
//    }

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
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}