package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.ShelvesItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.ShelvesListDetailView

interface ShelvesListDetailPresenter :IBasePresenter, BookItemDelegate
    , SortAndViewAsDelegate, ShelvesItemDelegate {
    fun initView(view: ShelvesListDetailView)
    fun saveShelfData(shelfVO: ShelfVO)
    fun deleteShelfData(shelfId:Int)
   // fun getAllBooksListFilterByCategory(chipName: String):List<BooksListVO>
   // fun getAllBooksListNoSelectedCategory():List<BooksListVO>

}