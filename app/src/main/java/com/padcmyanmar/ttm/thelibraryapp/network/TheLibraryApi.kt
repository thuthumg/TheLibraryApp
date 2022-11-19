package com.padcmyanmar.ttm.thelibraryapp.network

import com.padcmyanmar.ttm.thelibraryapp.network.responses.GoogleBooksListResponse
import com.padcmyanmar.ttm.thelibraryapp.network.responses.ListJsonResponse
import com.padcmyanmar.ttm.thelibraryapp.network.responses.OverviewResponse
import com.padcmyanmar.ttm.thelibraryapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TheLibraryApi {

    @GET(API_GET_OVERVIEW)
    fun getOverviewBooksList(
        @Query(PARAM_API_KEY) apiKey: String = LIBRARY_API_KEY,
        @Query(PARAM_PUBLISHED_DATE) publishedDate: String
    ): Observable<OverviewResponse>


    @GET(API_GET_LIST)
    fun getLists(
        @Query(PARAM_API_KEY) apiKey: String = LIBRARY_API_KEY,
        @Query(PARAM_LIST_NAME) list: String
    ): Observable<ListJsonResponse>

//      @Query(PARAM_PUBLISHED_DATE) publishedDate: String,


    @GET(API_GET_VOLUMES)
    fun googleBooksList(
        @Query(PARAM_Q) q:String
    ):Observable<GoogleBooksListResponse>
}