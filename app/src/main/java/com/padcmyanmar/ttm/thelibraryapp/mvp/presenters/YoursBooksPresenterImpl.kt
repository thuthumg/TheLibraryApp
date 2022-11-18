package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.YourBooksView

class YoursBooksPresenterImpl :ViewModel(), YoursBooksPresenter{

    //View
    var mView: YourBooksView?= null

    //Model
    private val mTheLibraryAppModel: TheLibraryAppModel =  TheLibraryAppModelImpl


    override fun initView(view: YourBooksView) {
       mView = view
    }

    override fun getAllBooksListFilterByCategory(chipName: String): List<BooksListVO> {
       return mTheLibraryAppModel.filterByCategory(chipName)
    }

    override fun getAllBooksListNoSelectedCategory(): List<BooksListVO> {
       return mTheLibraryAppModel.getAllBooksListNoSelectedCategory()
    }

    override fun onUiReady(owner: LifecycleOwner) {
        Log.d("yourbook","check ui ready")
       mTheLibraryAppModel.getReadBooksList {
           mView?.showError(it)
       }?.observe(owner){
           Log.d("yourbook","check ui ready observe")
           mView?.showReadBooksList(it)
       }
    }

    override fun callContextualMenuBottomSheetDialogFun(mBooksListVO: BooksListVO) {
        mView?.navigateToContextualMenuBottomSheetDialog(mBooksListVO)
    }

    override fun callMoreFunc() {
       // mView?.navigateToBooksMorePage()
    }

    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {

       mView?.navigateToBookDetailPage(mBooksListVO)
    }

    override fun addToShelvesList(mBooksListVO: BooksListVO) {
       mView?.navigateToAddToShelvesList(mBooksListVO)
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

    override fun callFilterByCategory(chipNameList: ArrayList<String>){

        mView?.callFilterByCategory(chipNameList)

    }


}