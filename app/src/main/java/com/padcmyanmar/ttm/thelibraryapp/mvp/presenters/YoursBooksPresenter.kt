package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.YourBooksView

interface YoursBooksPresenter:IBasePresenter, BookItemDelegate,
    SortAndViewAsDelegate {
    fun initView(view: YourBooksView)
    fun getAllBooksListFilterByCategory(chipName: String):List<BooksListVO>
    fun getAllBooksListNoSelectedCategory():List<BooksListVO>
}