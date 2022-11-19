package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.CreateShelfView

interface CreateShelfPresenter:IBasePresenter {

    fun initView(view:CreateShelfView)

    fun createShelfData(shelfVO: ShelfVO)

    fun callToBack()

}