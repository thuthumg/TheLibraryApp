package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.HomeView

class HomePresenterImpl : ViewModel(),HomePresenter {

    //View
    var mView: HomeView?= null

    //Model
    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl

    //States
    private var mCategoryBooksList: List<CategoryBooksListVO>? = listOf()

    override fun initView(view: HomeView) {
       mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

        mTheLibraryAppModel.getCategoryBooksList(
            published_date = "2022-11-14",
            onSuccess = {
            mCategoryBooksList = it
            mView?.showCategoryBooksList(it)
        },
        onFailure = {
            mView?.showError(it)
        })


        mTheLibraryAppModel.getReadBooksList {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showReadBooksList(it.reversed())
        }

    }

    override fun callContextualMenuBottomSheetDialogFun(mBooksListVO: BooksListVO) {
       mView?.navigateToContextualMenuBottomSheetDialog(mBooksListVO)
    }

    override fun callMoreFunc() {
        mView?.navigateToBooksMorePage()
    }

    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {
        mBooksListVO?.let {
            mTheLibraryAppModel.insertReadBook(
                it)
        }

       mView?.navigateToBookDetailPage(mBooksListVO)
    }

    override fun addToShelvesList(mBooksListVO: BooksListVO) {
       //
    }

    override fun deleteFromLibrary() {
       //
    }

    override fun aboutThisBook() {
      //
    }

//    ,
//                onSuccess = {
//                    mView?.showError(it)
//            },
//                onFailure = {
//                    mView?.showError(it)
//                }
}