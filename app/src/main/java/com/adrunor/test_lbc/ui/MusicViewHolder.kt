package com.adrunor.test_lbc.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adrunor.test_lbc.R
import com.adrunor.test_lbc.core.model.Music
import com.adrunor.test_lbc.databinding.ViewholderMusicBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders


class MusicViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding =  ViewholderMusicBinding.bind(view)

    fun bind(music: Music) {
        binding.musicTitle.text = music.title
        binding.musicAlbumId.text = music.albumId.toString()

        val url = GlideUrl(music.thumbnailUrl, LazyHeaders.Builder()
                .addHeader("User-Agent", "Mozilla/5.0 (Android; Tablet; rv:19.0) Gecko/19.0 Firefox/19.0")
                .build())

        Glide.with(itemView)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_music_placeholder)
            .into(binding.musicThumbnail)

    }
}