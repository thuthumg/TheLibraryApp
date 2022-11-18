package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.YourShelvesView

class YoursShelvesPresenterImpl:ViewModel(), YoursShelvesPresenter {


    //View
    var mView: YourShelvesView?= null

    //Model
    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl



    override fun initView(view: YourShelvesView) {
       mView = view
    }

    override fun getAllBooksListFilterByCategory(chipName: String): List<BooksListVO> {
        return mTheLibraryAppModel.filterByCategory(chipName)
    }

    override fun getAllBooksListNoSelectedCategory(): List<BooksListVO> {
        return mTheLibraryAppModel.getAllBooksListNoSelectedCategory()
    }

    override fun onUiReady(owner: LifecycleOwner) {
/*
        mTheLibraryAppModel.getReadBooksList {
            mView?.showError(it)
        }?.observe(owner){
            Log.d("yourbook","check ui ready observe")
            mView?.showReadBooksList(it)
        }
*/

        mTheLibraryAppModel.getShelvesList {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showShelvesListData(it)
        }
    }

    override fun callContextualMenuBottomSheetDialogFun(mBooksListVO: BooksListVO) {
        mView?.navigateToContextualMenuBottomSheetDialog(mBooksListVO)
    }

    override fun callMoreFunc() {

    }

    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {
        mView?.navigateToBookDetailPage(mBooksListVO)
    }

    override fun addToShelvesList(mBooksListVO: BooksListVO) {

    }

    override fun deleteFromLibrary() {
        TODO("Not yet implemented")
    }

    override fun aboutThisBook() {
        TODO("Not yet implemented")
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
            mView?.navigateToShelfListPage(textShelfName, mShelfVO)
    }

    override fun deleteShelf(shelfId: Int) {

    }

    override fun renameShelf() {

    }


}