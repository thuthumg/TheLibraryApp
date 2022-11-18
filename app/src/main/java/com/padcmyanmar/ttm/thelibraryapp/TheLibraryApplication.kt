package com.padcmyanmar.ttm.thelibraryapp

import android.app.Application
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl.initDatabase


class TheLibraryApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        initDatabase(applicationContext)
    }
}