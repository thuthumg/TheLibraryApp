package com.padcmyanmar.ttm.thelibraryapp.data.models

import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO

interface ShelvesDataModel {

    fun insertShelf(
        shelfName:String,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getShelvesList(
        onSuccess: (shelvesList:List<ShelfVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun deleteShelf(
        shelvesId:Int,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )
}