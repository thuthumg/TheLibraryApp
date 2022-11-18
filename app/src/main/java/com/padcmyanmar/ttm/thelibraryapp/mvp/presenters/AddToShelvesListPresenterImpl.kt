package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.AddToShelvesListView

class AddToShelvesListPresenterImpl:ViewModel(),AddToShelvesListPresenter {


    //View
    var mView:AddToShelvesListView ?= null

    //Model
    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl


    override fun initView(view: AddToShelvesListView) {
       mView = view
    }

    override fun saveShelfData(shelvesList: List<ShelfVO>) {
        mTheLibraryAppModel.insertAllShelf(shelvesList,
            onSuccess = {
                mView?.showError("success")
            },
            onFailure = {
                mView?.showError(it)
            })
    }


    override fun onUiReady(owner: LifecycleOwner) {
       mTheLibraryAppModel.getShelvesList {
           mView?.showError(it)
       }?.observe(owner){
           mView?.showShelvesListData(it)
       }
    }

    override fun addToShelvesData(shelfId: Int, selectedShelfData: Boolean) {
        mView?.addToShelvesData(shelfId, selectedShelfData)
    }
}