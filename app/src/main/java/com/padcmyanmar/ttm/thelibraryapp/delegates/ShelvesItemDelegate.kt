package com.padcmyanmar.ttm.thelibraryapp.delegates

import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO

interface ShelvesItemDelegate {

    fun goToShelvesDetailPage(textShelfName: String, mShelfVO: ShelfVO?)
    fun deleteShelf(shelfId:Int)
    fun renameShelf()
}