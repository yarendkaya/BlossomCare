package com.demirkayayaren.blossomcare.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.demirkayayaren.blossomcare.adapter.BlossomAdapter
import com.demirkayayaren.blossomcare.data.model.Blossom
import com.demirkayayaren.blossomcare.data.network.NetworkResult
import com.demirkayayaren.blossomcare.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BlossomViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        viewModel.getAllBlossoms()
//        observeBlossomResponse()

        viewModel.fetchResult()
        observeBlossom()
    }

    private fun observeBlossom() {
        viewModel.blossomResponse.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    setAdapter(response.data?.blossomList)
                    Log.e("MainActivity", "Success")
                }

                is NetworkResult.Error -> {
                    Log.e("MainActivity", "Error: ${response.message}")
                }

                is NetworkResult.Loading -> {
                    Log.e("MainActivity", "Loading")
                }
            }
        }
    }

//    private fun observeBlossomResponse() {
//        viewModel.blossomResponse.observe(this) { response ->
//            if (response.isSuccessful) {
//                val blossomResponse = response.body()
//                setAdapter(blossomResponse?.blossomList)
//                Log.e("MainActivity", "Success")
//            } else {
//                val errorMessage = response.message()
//                Log.e("MainActivity", "Error: $errorMessage")
//                // Handle error case (e.g., show a toast or error message)
//            }
//        }
//    }

    private fun setAdapter(data: List<Blossom>?) {
        data?.let {
            val adapter = BlossomAdapter()
            adapter.setData(it.toMutableList())
            binding.rvBlossomResult.adapter = adapter
        }
    }
}