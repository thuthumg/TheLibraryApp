package com.padcmyanmar.ttm.thelibraryapp.data.models

import android.content.Context
import com.padcmyanmar.ttm.thelibraryapp.network.TheLibraryApi
import com.padcmyanmar.ttm.thelibraryapp.persistence.TheLibraryAppDatabase
import com.padcmyanmar.ttm.thelibraryapp.utils.BASE_URL
import com.padcmyanmar.ttm.thelibraryapp.utils.GOOGLE_BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

abstract class BaseModel {

    protected var mTheLibraryApi: TheLibraryApi
    protected var mTheLibraryGoogleApi:TheLibraryApi
    protected var mTheLibraryAppDatabase: TheLibraryAppDatabase?= null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val retrofitForGoogleLink = Retrofit.Builder()
            .baseUrl(GOOGLE_BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        mTheLibraryApi = retrofit.create(TheLibraryApi::class.java)
        mTheLibraryGoogleApi = retrofitForGoogleLink.create(TheLibraryApi::class.java)
    }

    fun initDatabase(context: Context){
        mTheLibraryAppDatabase = TheLibraryAppDatabase.getDBInstance(context)
    }
}