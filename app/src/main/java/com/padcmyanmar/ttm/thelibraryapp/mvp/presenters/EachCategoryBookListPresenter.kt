package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.EachCategoryView

interface EachCategoryBookListPresenter: IBasePresenter, BookItemDelegate {

    fun initView(view:EachCategoryView)

    fun onUiReadyWithListName(owner: LifecycleOwner, listName: String)

}