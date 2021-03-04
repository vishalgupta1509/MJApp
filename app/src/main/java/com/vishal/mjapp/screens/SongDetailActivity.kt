package com.vishal.mjapp.screens

import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.vishal.mjapp.R
import com.vishal.mjapp.baseFiles.BaseActivity
import com.vishal.mjapp.databinding.ActivitySongDetailBinding
import com.vishal.mjapp.models.Song
import com.vishal.mjapp.utils.AppConstants.SONG_DETAIL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongDetailActivity : BaseActivity() {
    private lateinit var mBinding: ActivitySongDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_song_detail)
        mBinding.lifecycleOwner = this

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val song = intent.getSerializableExtra(SONG_DETAIL)!!
        mBinding.song = song as Song
        setImage(song.artworkUrl100)
    }

    private fun setImage(url: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Glide.with(this@SongDetailActivity)
                .load(url)
                .placeholder(AppCompatResources.getDrawable(this@SongDetailActivity, R.drawable.user_placeholder)
                ).into(mBinding.imageViewSong)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}