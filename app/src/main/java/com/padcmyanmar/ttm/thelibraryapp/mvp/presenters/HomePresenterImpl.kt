package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.HomePageModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.HomePageModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.HomeView

class HomePresenterImpl : ViewModel(),HomePresenter {

    //View
    var mView: HomeView?= null

    //Model
    private val mHomePageModel: HomePageModel = HomePageModelImpl

    //States
    private var mCategoryBooksList: List<CategoryBooksListVO>? = listOf()

    override fun initView(view: HomeView) {
       mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

        mHomePageModel.getCategoryBooksList(
            published_date = "2022-11-14",
            onSuccess = {
            mCategoryBooksList = it
            mView?.showCategoryBooksList(it)
        },
        onFailure = {
            mView?.showError(it)
        })


        mHomePageModel.getReadBooksList {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showReadBooksList(it)
        }

    }

    override fun callContextualMenuBottomSheetDialogFun() {
       mView?.navigateToContextualMenuBottomSheetDialog()
    }

    override fun callMoreFunc() {
        mView?.navigateToBooksMorePage()
    }

    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {
        mBooksListVO?.let {
            mHomePageModel.insertReadBook(
                it,
                onSuccess = {
                    mView?.showError(it)
            },
                onFailure = {
                    mView?.showError(it)
                })
        }

       mView?.navigateToBookDetailPage(mBooksListVO)
    }
}