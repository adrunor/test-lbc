package com.adrunor.test_lbc.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adrunor.test_lbc.R
import com.adrunor.test_lbc.core.model.Music
import com.adrunor.test_lbc.databinding.ViewholderMusicBinding
import com.bumptech.glide.Glide

class MusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding =  ViewholderMusicBinding.bind(view)

    fun bind(music: Music) {
        binding.musicTitle.text = music.title
        binding.musicAlbumId.text = music.albumId.toString()

        Glide.with(itemView)
            .load(music.thumbnailUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_music_placeholder)
            .into(binding.musicThumbnail)

    }
}