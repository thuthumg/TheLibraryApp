package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {

    fun onUiReady(owner: LifecycleOwner)
}