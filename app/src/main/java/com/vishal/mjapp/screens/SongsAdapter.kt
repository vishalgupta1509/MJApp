package com.vishal.mjapp.screens

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vishal.mjapp.R
import com.vishal.mjapp.databinding.RecyclerItemSongBinding
import com.vishal.mjapp.models.Song
import com.vishal.mjapp.utils.AppConstants.SONG_DETAIL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongsAdapter(private val songs: ArrayList<Song>): RecyclerView.Adapter<SongsAdapter.SongsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        val binding = RecyclerItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.bind(songs[position])
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    fun onSongClick(context: Context, song: Song) {
        context.startActivity(Intent(context, SongDetailActivity::class.java).putExtra(SONG_DETAIL, song))
    }

    inner class SongsViewHolder(private val binding: RecyclerItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.song = song
            binding.context = itemView.context
            binding.adapter = this@SongsAdapter
            binding.textViewSongName.isSelected = true      // Marqueeing Text
            setImage(song.artworkUrl100)
        }

        private fun setImage(url: String) {
            CoroutineScope(Dispatchers.Main).launch {
                Glide.with(itemView.context)
                        .load(url)
                        .placeholder(AppCompatResources.getDrawable(itemView.context, R.drawable.user_placeholder)
                        ).into(binding.imageViewSong)
            }
        }
    }
}