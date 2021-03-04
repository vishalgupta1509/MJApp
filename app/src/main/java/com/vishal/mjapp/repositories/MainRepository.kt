package com.vishal.mjapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.vishal.mjapp.models.ResponseData
import com.vishal.mjapp.network.ApiInterface
import com.vishal.mjapp.network.ApiResponse
import com.vishal.mjapp.network.NetworkBoundResource
import com.vishal.mjapp.network.Resource
import com.vishal.mjapp.utils.AppExecutors
import org.koin.java.KoinJavaComponent.inject

class MainRepository {
    val apiInterface : ApiInterface by inject(ApiInterface::class.java)
    val appExecutors : AppExecutors by inject(AppExecutors::class.java)

    fun getMJSongs(): LiveData<Resource<ResponseData>> {
        return object : NetworkBoundResource<ResponseData, ResponseData>(appExecutors) {
            override fun createCall(): LiveData<ApiResponse<ResponseData>> = apiInterface.getMJSongs()
            override fun onFetchFailed() {
                Log.e(MainRepository::class.java.simpleName, "onFetchFailed getMJSongs")
            }
        }.asLiveData()
    }
}