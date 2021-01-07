package com.adrunor.test_lbc.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adrunor.test_lbc.R
import com.adrunor.test_lbc.core.model.Music

class PlaylistAdapter : RecyclerView.Adapter<MusicViewHolder>() {


    private var musicList = listOf<Music>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_music, parent, false))
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        val music = musicList[position]
        holder.bind(music)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    fun setMusicList(musicList: List<Music>) {
        this.musicList = musicList
        notifyDataSetChanged()
    }

}