package com.vishal.mjapp.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.mjapp.models.Song
import com.vishal.mjapp.network.Status.ERROR
import com.vishal.mjapp.network.Status.SUCCESS
import com.vishal.mjapp.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository): ViewModel() {
    val mutableSongData: MutableLiveData<ArrayList<Song>> = MutableLiveData()
    val mutableError: MutableLiveData<String> = MutableLiveData()

    fun getMJSongs() {
        viewModelScope.launch {
            repository.getMJSongs().observeForever {
                it?.let {
                    when(it.status) {
                        SUCCESS -> {
                            mutableSongData.value = it.data?.results as ArrayList<Song>
                        }
                        ERROR -> {
                            mutableError.value = it.message
                        }
                    }
                }
            }
        }
    }
}