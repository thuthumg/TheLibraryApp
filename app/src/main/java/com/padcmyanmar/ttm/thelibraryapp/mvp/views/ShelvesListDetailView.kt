package com.padcmyanmar.ttm.thelibraryapp.mvp.views

import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO

interface ShelvesListDetailView:BaseView {


    fun showShelvesListData(shelvesList: List<ShelfVO>)
    fun showReadBooksList(readBooksListVO: List<BooksListVO>)
    fun navigateToContextualMenuBottomSheetDialog(mBooksListVO: BooksListVO?)
    fun viewAsFuncListener(checkedViewAsRadioButtonText:String)
    fun sortFuncListener(checkedViewAsRadioButtonText:String)

    fun navigateToViewAsBottomSheetDialog(changeListViewType: String)
    fun navigateToSortByBottomSheetDialog(changeSortType: String)
    fun navigateToBookDetailPage(mBooksListVO: BooksListVO?)

    fun callFilterByCategory(chipNameList:ArrayList<String>)

    fun navigateToShelfListPage(textShelfName: String)

    fun changeToShelfNameUI()

    fun deleteShelf(shelfId:Int)


}