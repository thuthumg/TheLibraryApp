package com.padcmyanmar.ttm.thelibraryapp.data.models

import android.content.Context
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.persistence.TheLibraryAppDatabase

object ShelvesDataModelImpl:ShelvesDataModel {

    //Database Dependency
    private var mTheLibraryAppDatabase: TheLibraryAppDatabase? = null

    fun initDatabase(context: Context) {
        mTheLibraryAppDatabase = TheLibraryAppDatabase.getDBInstance(context)
   }

    override fun insertShelf(
        shelfName: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        var shelfVO = ShelfVO(1,shelfName)
        mTheLibraryAppDatabase?.shelvesDao()?.insertShelves(
            shelfVO
        )
        onSuccess("success")
      //  onFailure("fail")


    }

    override fun getShelvesList(onSuccess: (shelvesList:List<ShelfVO>) -> Unit, onFailure: (String) -> Unit) {

        onSuccess(
            mTheLibraryAppDatabase?.shelvesDao()?.getAllShelves(
            ) ?: listOf()
        )

    }

    override fun deleteShelf(
        shelvesId: Int,onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {

        mTheLibraryAppDatabase?.shelvesDao()?.deleteByShelfId(shelvesId)
        onSuccess("success")
    }


}