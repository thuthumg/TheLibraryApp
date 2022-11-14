package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.HomeView

interface HomePresenter: IBasePresenter, BookItemDelegate {

    fun initView(view: HomeView)
}