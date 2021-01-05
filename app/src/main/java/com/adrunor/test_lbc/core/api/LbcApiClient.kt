package com.adrunor.test_lbc.core.api

import com.adrunor.test_lbc.BuildConfig
import com.adrunor.test_lbc.core.model.Music
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LbcApiClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val api = retrofit.create(LbcApi::class.java)

    fun getPlaylist(): Single<List<Music>> {
        return api.getPlaylist()
    }

    companion object {
        private const val URL = BuildConfig.API_URL

        @Volatile private var INSTANCE: LbcApiClient? = null

        fun getInstance(): LbcApiClient =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LbcApiClient().also { INSTANCE = it }
            }
    }
}