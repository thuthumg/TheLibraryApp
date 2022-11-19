package com.padcmyanmar.ttm.thelibraryapp.delegates

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

interface GoogleBookItemDelegate {

    fun goToDetailPage(mBooksListVO: BooksListVO?)
}