package com.adrunor.test_lbc.core.api

import com.adrunor.test_lbc.core.model.Music
import io.reactivex.Single
import retrofit2.http.GET

interface LbcApi {

    @GET("img/shared/technical-test.json")
    fun getPlaylist(): Single<List<Music>>
}