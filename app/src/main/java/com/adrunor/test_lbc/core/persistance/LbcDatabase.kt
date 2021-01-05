package com.adrunor.test_lbc.core.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adrunor.test_lbc.core.model.Music
import com.adrunor.test_lbc.core.persistance.dao.MusicDao

@Database(
    entities = [Music::class],
    version = 1
)
abstract class LbcDatabase : RoomDatabase() {
    abstract fun musicDao(): MusicDao

    companion object {

        @Volatile private var INSTANCE: LbcDatabase? = null

        fun getInstance(context: Context): LbcDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                LbcDatabase::class.java, "lbc.db").build()
    }
}