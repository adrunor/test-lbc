package com.adrunor.test_lbc.core.repository

import com.adrunor.test_lbc.core.api.LbcApiClient
import com.adrunor.test_lbc.core.model.Music
import com.adrunor.test_lbc.core.persistance.dao.MusicDao
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MusicRepository(private val api: LbcApiClient, private val dao: MusicDao) {

    fun fetchPlaylist(): Single<List<Music>> {
        return api.getPlaylist()
    }

    fun saveAll(musicList: List<Music>): Completable {
        return dao.insertAll(musicList)
    }

    fun getPlaylist(): Observable<List<Music>> {
        return dao.getAll()
    }

    fun searchMusic(query: String): Observable<List<Music>> {
        return dao.searchMusic(query)
    }
}