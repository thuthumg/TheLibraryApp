package com.padcmyanmar.ttm.thelibraryapp.delegates

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

interface BookItemDelegate {

    fun callContextualMenuBottomSheetDialogFun(mBooksListVO: BooksListVO)
    fun callMoreFunc()
    fun callBookDetailPage(mBooksListVO: BooksListVO?)

    fun addToShelvesList(mBooksListVO: BooksListVO)
    fun deleteFromLibrary()
  //  fun downloadBook()
  //  fun removeDownload()
    fun aboutThisBook()


}