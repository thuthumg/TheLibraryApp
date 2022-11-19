package com.padcmyanmar.ttm.thelibraryapp.mvp.views

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

interface SearchBookView:BaseView {


    fun navigateToBookDetailPage(mBooksListVO: BooksListVO?)


}