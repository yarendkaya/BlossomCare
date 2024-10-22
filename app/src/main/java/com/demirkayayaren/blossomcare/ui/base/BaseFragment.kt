package com.demirkayayaren.blossomcare.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.demirkayayaren.blossomcare.ui.BlossomViewModel

abstract class BaseFragment<T : ViewBinding>(private val bindingInflater: (inflater: LayoutInflater) -> T) :
    Fragment() {

    private var _binding: T? = null //bundan private adını kaldırdım bu bir problem mi ?

    val binding: T
        get() = _binding as T// bunu neden nulla cekmem gerektiğini anlamadım

    abstract var viewModel: BlossomViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BlossomViewModel::class.java)
        initArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    open fun setupUI() = Unit

    open fun initArgs() = Unit
}
