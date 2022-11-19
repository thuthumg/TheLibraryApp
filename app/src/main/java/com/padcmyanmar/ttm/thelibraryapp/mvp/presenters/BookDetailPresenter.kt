package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import com.padcmyanmar.ttm.thelibraryapp.mvp.views.BookDetailView

interface BookDetailPresenter:IBasePresenter {

    fun initView(view:BookDetailView)

    fun callRatingAndReviewPage()
    fun callAboutPage(description: String?)
    fun callBack()

}