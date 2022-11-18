package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.AddToShelvesListDelegate
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.AddToShelvesListView

interface AddToShelvesListPresenter:IBasePresenter,AddToShelvesListDelegate  {

    fun initView(view:AddToShelvesListView)
    fun saveShelfData(shelvesList: List<ShelfVO>)
}