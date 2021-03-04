package com.vishal.mjapp.network

import androidx.lifecycle.LiveData
import com.vishal.mjapp.models.ResponseData
import com.vishal.mjapp.utils.AppConstants.SONG_URL
import retrofit2.http.GET

interface ApiInterface {

    @GET(SONG_URL)
    fun getMJSongs(): LiveData<ApiResponse<ResponseData>>
}