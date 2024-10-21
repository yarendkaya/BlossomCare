package com.demirkayayaren.blossomcare.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.demirkayayaren.blossomcare.ui.BlossomViewModel

abstract class BaseFragment<T : ViewBinding>(private val bindingInflater: (inflater: LayoutInflater) -> T) :
    Fragment() {

    private var _binding: T? = null

    val binding: T
        get() = _binding as T

    abstract var viewModel: BlossomViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize shared ViewModel (if required by child fragments)
        viewModel = ViewModelProvider(requireActivity()).get(BlossomViewModel::class.java)

    }

    override fun onDestroy() {
        super.onDestroy()
        // Log fragment destruction for debugging
        Log.d("BaseFragment", "Fragment Destroyed: ${this::class.java.simpleName}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null) {
            throw IllegalArgumentException("Binding cannot be null")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    open fun setupUI() = Unit
}
