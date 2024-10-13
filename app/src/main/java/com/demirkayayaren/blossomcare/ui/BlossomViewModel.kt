package com.demirkayayaren.blossomcare.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demirkayayaren.blossomcare.data.model.BlossomResponse
import com.demirkayayaren.blossomcare.data.network.NetworkResult
import com.demirkayayaren.blossomcare.data.repository.BlossomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlossomViewModel @Inject constructor (private val repository: BlossomRepository): ViewModel() {

    private val _blossomResponseResponse = MutableLiveData<NetworkResult<BlossomResponse>>()
    val blossomResponse: LiveData<NetworkResult<BlossomResponse>> = _blossomResponseResponse


    fun fetchResult() {
        _blossomResponseResponse.value = NetworkResult.Loading()
        viewModelScope.launch {
            if (true) {
                try {
                    val result = repository.getAllBlossoms()
                    if (result.data != null) {
                        _blossomResponseResponse.postValue(NetworkResult.Success(result.data))
                    } else {
                        _blossomResponseResponse.value = NetworkResult.Error("Veri bulunamadı")
                    }
                } catch (e: Exception) {
                    _blossomResponseResponse.value =
                        NetworkResult.Error(e.message ?: "Bilinmeyen bir hata oluştu")
                }
            } else {
                _blossomResponseResponse.value = NetworkResult.Error("İnternet bağlantısı yok!")
            }
        }
    }


//    fun getAllBlossoms() {
//        viewModelScope.launch {
//            val response = repository.getAllBlossoms()
//            if (response.isSuccessful) {
//                val blossomResponse = response.body()
//                _blossomResponseResponse.postValue(response)
//
//                Log.d("BlossomViewModel", "Blossom Response: $blossomResponse")
//            } else {
//                Log.e("BlossomViewModel", "Error: ${response.code()}")
//            }
//        }
//    }
}
