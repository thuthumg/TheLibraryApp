package com.padcmyanmar.ttm.thelibraryapp.mvp.views

import androidx.lifecycle.LiveData
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

interface YourBooksView : BaseView{

    fun showReadBooksList(readBooksListVO: List<BooksListVO>)
    fun navigateToContextualMenuBottomSheetDialog(mBooksListVO: BooksListVO?)

    fun viewAsFuncListener(checkedViewAsRadioButtonText:String)
    fun sortFuncListener(checkedViewAsRadioButtonText:String)

    fun navigateToViewAsBottomSheetDialog(changeListViewType: String)
    fun navigateToSortByBottomSheetDialog(changeSortType: String)
    fun navigateToBookDetailPage(mBooksListVO: BooksListVO?)

    fun callFilterByCategory(chipNameList:ArrayList<String>)

    fun navigateToAddToShelvesList(mBooksListVO: BooksListVO?)

}