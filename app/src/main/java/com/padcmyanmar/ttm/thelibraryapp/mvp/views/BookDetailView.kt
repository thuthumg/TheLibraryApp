package com.padcmyanmar.ttm.thelibraryapp.mvp.views

import androidx.lifecycle.LifecycleOwner

interface BookDetailView:BaseView {

   fun callRatingAndReviewPage()
   fun callAboutPage(sDesc:String?)
   fun callBack()



}