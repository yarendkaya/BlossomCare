package com.demirkayayaren.blossomcare.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demirkayayaren.blossomcare.data.model.BlossomFav
import com.demirkayayaren.blossomcare.data.model.BlossomResponse
import com.demirkayayaren.blossomcare.data.network.NetworkResult
import com.demirkayayaren.blossomcare.data.repository.BlossomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlossomViewModel @Inject constructor(private val repository: BlossomRepository) :
    ViewModel() {

    private val _blossomResponseResponse = MutableLiveData<NetworkResult<BlossomResponse>>()
    val blossomResponse: LiveData<NetworkResult<BlossomResponse>> = _blossomResponseResponse

    private val _favoriteBlossoms: MutableLiveData<List<BlossomFav>> = MutableLiveData()
    val favoriteBlossoms: LiveData<List<BlossomFav>> = _favoriteBlossoms

    fun fetchResult() {
        _blossomResponseResponse.value = NetworkResult.Loading()
        viewModelScope.launch {
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
        }
    }

    fun saveBlossom(blossom: BlossomFav) {
        viewModelScope.launch {
            repository.saveBlossom(blossom)
        }
    }

    fun deleteBlossom(blossom: BlossomFav) {
        viewModelScope.launch {
            repository.deleteBlossom(blossom)
        }
    }

    fun getAllSavedBlossoms() {
        viewModelScope.launch {
            _favoriteBlossoms.value = repository.getAllSavedBlossoms()
        }
    }
}
