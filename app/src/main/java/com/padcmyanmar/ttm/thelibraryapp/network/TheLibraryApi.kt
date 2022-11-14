package com.padcmyanmar.ttm.thelibraryapp.network

import com.padcmyanmar.ttm.thelibraryapp.network.responses.OverviewResponse
import com.padcmyanmar.ttm.thelibraryapp.utils.API_GET_OVERVIEW
import com.padcmyanmar.ttm.thelibraryapp.utils.LIBRARY_API_KEY
import com.padcmyanmar.ttm.thelibraryapp.utils.PARAM_API_KEY
import com.padcmyanmar.ttm.thelibraryapp.utils.PARAM_PUBLISHED_DATE
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TheLibraryApi {

    @GET(API_GET_OVERVIEW)
    fun getOverviewBooksList(
        @Query(PARAM_API_KEY) apiKey: String = LIBRARY_API_KEY,
        @Query(PARAM_PUBLISHED_DATE) publishedDate: String
    ): Observable<OverviewResponse>


}