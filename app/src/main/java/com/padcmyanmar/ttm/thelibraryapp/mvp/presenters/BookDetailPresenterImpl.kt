package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.BookDetailView

class BookDetailPresenterImpl:ViewModel(),BookDetailPresenter {


    //View
    var mView:BookDetailView ?= null

    //Model
    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl


    override fun initView(view: BookDetailView) {
        mView = view
    }

    override fun callRatingAndReviewPage() {
        mView?.callRatingAndReviewPage()
    }

    override fun callAboutPage() {
        mView?.callAboutPage()
    }

    override fun callBack() {
        mView?.callBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}