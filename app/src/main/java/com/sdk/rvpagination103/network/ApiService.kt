package com.sdk.rvpagination103.network

import com.sdk.rvpagination103.model.RickAndMortyList
import com.sdk.rvpagination103.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getAllRickAndMorty(
        @Query("page") page: Int
    ): RickAndMortyList
}