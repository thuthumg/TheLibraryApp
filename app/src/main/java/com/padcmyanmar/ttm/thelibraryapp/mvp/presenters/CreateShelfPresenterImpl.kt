package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.CreateShelfView

class CreateShelfPresenterImpl:ViewModel(),CreateShelfPresenter {


    //View
    var mView:CreateShelfView ?= null

    //Model
    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl

    override fun initView(view: CreateShelfView) {
        mView = view
    }

    override fun createShelfData(shelfVO: ShelfVO) {
       mTheLibraryAppModel.insertShelf(shelfVO,
       onSuccess = {
                  // mView?.showError("success")
       },
       onFailure = {
           mView?.showError(it)
       })
    }

    override fun callToBack() {
       mView?.navigateToBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}