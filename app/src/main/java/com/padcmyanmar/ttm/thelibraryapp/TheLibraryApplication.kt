package com.padcmyanmar.ttm.thelibraryapp

import android.app.Application
import com.padcmyanmar.ttm.thelibraryapp.data.models.ShelvesDataModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.ShelvesDataModelImpl
import com.padcmyanmar.ttm.thelibraryapp.persistence.TheLibraryAppDatabase

class TheLibraryApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        ShelvesDataModelImpl.initDatabase(applicationContext)
    }
}