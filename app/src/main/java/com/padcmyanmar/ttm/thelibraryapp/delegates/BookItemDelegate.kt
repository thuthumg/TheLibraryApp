package com.padcmyanmar.ttm.thelibraryapp.delegates

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

interface BookItemDelegate {

    fun callContextualMenuBottomSheetDialogFun(mBooksListVO: BooksListVO)
    fun callMoreFunc(listName: String?, listId: Int?)
    fun callBookDetailPage(mBooksListVO: BooksListVO?)

    fun addToShelvesList(mBooksListVO: BooksListVO)
    fun deleteFromLibrary(id: Int)
  //  fun downloadBook()
  //  fun removeDownload()
    fun aboutThisBook()


}