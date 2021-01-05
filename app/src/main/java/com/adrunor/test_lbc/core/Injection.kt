package com.adrunor.test_lbc.core

import android.content.Context
import com.adrunor.test_lbc.core.api.LbcApiClient
import com.adrunor.test_lbc.core.persistance.LbcDatabase
import com.adrunor.test_lbc.core.repository.MusicRepository

object Injection {

    fun provideMusicDataSource(context: Context): MusicRepository {
        return MusicRepository(LbcApiClient.getInstance(), LbcDatabase.getInstance(context).musicDao())
    }
}