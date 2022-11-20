package com.padcmyanmar.ttm.thelibraryapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.thelibraryapp.data.vos.GoogleBookVO

data class GoogleBooksListResponse(

    @SerializedName("items")
    var items: List<GoogleBookVO>?
){

}
