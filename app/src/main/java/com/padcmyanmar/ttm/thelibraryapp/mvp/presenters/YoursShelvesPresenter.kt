package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.ShelvesItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.YourShelvesView

interface YoursShelvesPresenter:IBasePresenter,BookItemDelegate
    ,SortAndViewAsDelegate, ShelvesItemDelegate {
    fun initView(view:YourShelvesView)
    fun getAllBooksListFilterByCategory(chipName: String):List<BooksListVO>
    fun getAllBooksListNoSelectedCategory():List<BooksListVO>

}