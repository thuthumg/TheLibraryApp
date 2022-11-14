package com.padcmyanmar.ttm.thelibraryapp.mvp.views

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO

interface HomeView : BaseView{

    fun showCategoryBooksList(categoryBooksList: List<CategoryBooksListVO>)
    fun navigateToContextualMenuBottomSheetDialog()
    fun navigateToBooksMorePage()
    fun navigateToBookDetailPage(mBooksListVO: BooksListVO?)

    fun showReadBooksList(readBooksListVO: List<BooksListVO>)

}