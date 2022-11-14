package com.padcmyanmar.ttm.thelibraryapp.delegates

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

interface BookItemDelegate {

    fun callContextualMenuBottomSheetDialogFun()
    fun callMoreFunc()

    fun callBookDetailPage(mBooksListVO: BooksListVO?)


}