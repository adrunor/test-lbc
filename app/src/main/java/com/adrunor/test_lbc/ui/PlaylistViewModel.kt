package com.adrunor.test_lbc.ui

import androidx.lifecycle.ViewModel
import com.adrunor.test_lbc.core.model.Music
import com.adrunor.test_lbc.core.repository.MusicRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class PlaylistViewModel(private val repository: MusicRepository) : ViewModel() {

    fun getPlaylist(): Observable<List<Music>> {
        return repository.getPlaylist()
    }

    fun fetchPlaylist(): Single<List<Music>> {
        return repository.fetchPlaylist()
    }

    fun saveAll(playlist: List<Music>): Completable {
        return repository.saveAll(playlist)
    }

    fun searchMusic(query: String): Observable<List<Music>> {
        return repository.searchMusic(query)
    }
}