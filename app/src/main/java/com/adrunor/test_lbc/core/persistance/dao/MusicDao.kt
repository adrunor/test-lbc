package com.adrunor.test_lbc.core.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adrunor.test_lbc.core.model.Music
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(musicList: List<Music>): Completable

    @Query("SELECT * FROM music")
    fun getAll(): Observable<List<Music>>
}