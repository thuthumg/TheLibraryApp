package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.ShelvesListDetailView


class ShelvesListDetailPresenterImpl:ViewModel(),ShelvesListDetailPresenter{

  //View
    var mView: ShelvesListDetailView ?= null


    //Model
    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl

    override fun initView(view: ShelvesListDetailView) {
        mView = view
    }

    override fun saveShelfData(shelfVO: ShelfVO) {
       mTheLibraryAppModel.insertShelf(shelfVO, onSuccess = {}, onFailure = {})
    }

    override fun deleteShelfData(shelfId: Int) {
        mTheLibraryAppModel.deleteShelf(shelfId,onSuccess = {}, onFailure = {})
    }


//    override fun getAllBooksListFilterByCategory(chipName: String): List<BooksListVO> {
//        return mTheLibraryAppModel.filterByCategory(chipName)
//    }
//
//    override fun getAllBooksListNoSelectedCategory(): List<BooksListVO> {
//        return mTheLibraryAppModel.getAllBooksListNoSelectedCategory()
//    }

    override fun onUiReady(owner: LifecycleOwner) {
        mTheLibraryAppModel.getReadBooksList {
            mView?.showError(it)
        }?.observe(owner){

            mView?.showReadBooksList(it)
        }

    }

    override fun callContextualMenuBottomSheetDialogFun(mBooksListVO: BooksListVO) {
        mView?.navigateToContextualMenuBottomSheetDialog(mBooksListVO)
    }

    override fun callMoreFunc(listName: String?, listId: Int?) {

    }

    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {
        mView?.navigateToBookDetailPage(mBooksListVO)
    }

    override fun addToShelvesList(mBooksListVO: BooksListVO) {
        mView?.navigateToAddToShelvesList(mBooksListVO)
    }

    override fun deleteFromLibrary(id: Int) {
        mTheLibraryAppModel.deleteFromLibrary(id, onSuccess = {
            mView?.showError("successfully deleted!")
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun aboutThisBook() {

    }

    override fun callbackViewAsFunc(checkedViewAsRadioButtonText: String) {
        mView?.viewAsFuncListener(checkedViewAsRadioButtonText)
    }

    override fun callbackSortFunc(checkedViewAsRadioButtonText: String) {
        mView?.sortFuncListener(checkedViewAsRadioButtonText)
    }

    override fun callViewAsBottomSheetDialog(changeListViewType: String) {
        mView?.navigateToViewAsBottomSheetDialog(changeListViewType)
    }

    override fun callSortByBottomSheetDialog(changeSortType: String) {
        mView?.navigateToSortByBottomSheetDialog(changeSortType)
    }

    override fun callFilterByCategory(chipNameList: ArrayList<String>) {
        mView?.callFilterByCategory(chipNameList)
    }

    override fun goToShelvesDetailPage(textShelfName: String, mShelfVO: ShelfVO?) {
        mView?.navigateToShelfListPage(textShelfName)
    }

    override fun deleteShelf(shelfId: Int) {
      //  mTheLibraryAppModel.deleteShelf(shelfId,{},{})

        mView?.deleteShelf(shelfId)
    }

    override fun renameShelf() {
        mView?.changeToShelfNameUI()
    }

}