package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.GoogleBookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.SearchBookView
import io.reactivex.rxjava3.core.Observable

interface SearchBookPresenter:IBasePresenter, GoogleBookItemDelegate {
    fun initView(view:SearchBookView)
    fun saveReadBook(booksVO: BooksListVO)
    fun searchGoogleBooksList(searchQueryText:String): Observable<List<BooksListVO>>?
}