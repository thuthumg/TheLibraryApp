package com.padcmyanmar.ttm.thelibraryapp.mvp.views

import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO

interface AddToShelvesListView:BaseView{

    fun showShelvesListData(shelvesList: List<ShelfVO>)

    fun addToShelvesData(shelfId: Int, selectedShelfData: Boolean)
}