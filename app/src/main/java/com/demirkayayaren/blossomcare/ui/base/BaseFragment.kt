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

    var _binding: T? = null //bundan private adını kaldırdım bu bir problem mi ?

    val binding: T
        get() = _binding as T// bunu neden nulla cekmem gerektiğini anlamadım

    abstract var viewModel: BlossomViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(BlossomViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy() //logu kaldırınca neden override fun silik yazıyo ?
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
