package com.padcmyanmar.ttm.thelibraryapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.thelibraryapp.data.vos.EachCategoryBooksListVO

data class ListJsonResponse(

    @SerializedName("results")
    var results:List<EachCategoryBooksListVO>?
)
