package com.padcmyanmar.ttm.thelibraryapp.mvp.views

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

interface EachCategoryView:BaseView {

    fun showEachCategoryBookListData(booksListVOList:List<BooksListVO>)
    fun navigateToContextualMenuBottomSheetDialog(mBooksListVO: BooksListVO)
    fun navigateToBookDetailPage(mBooksListVO: BooksListVO?)
    fun navigateToAddToShelvesList(mBooksListVO: BooksListVO)
}