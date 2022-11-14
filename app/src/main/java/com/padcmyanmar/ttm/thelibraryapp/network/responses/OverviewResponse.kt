package com.padcmyanmar.ttm.thelibraryapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksVO

data class OverviewResponse(

    @SerializedName("status")
    val status : String?,

    @SerializedName("results")
    val results: CategoryBooksVO?
)
