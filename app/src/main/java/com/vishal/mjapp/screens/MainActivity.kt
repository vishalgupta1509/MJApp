package com.vishal.mjapp.screens

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.vishal.mjapp.R
import com.vishal.mjapp.baseFiles.BaseActivity
import com.vishal.mjapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.lifecycleOwner = this
        init()
    }

    private fun init() {
        showProgress()
        viewModel.getMJSongs()
        observers()
    }

    private fun observers() {
        viewModel.mutableSongData.observe(this, {
            it?.let {
                dismissProgress()
                mBinding.recyclerView.adapter = SongsAdapter(it)
            }
        })

        viewModel.mutableError.observe(this, {
            dismissProgress()
            it?.let {string ->
                Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
            }
        })
    }
}