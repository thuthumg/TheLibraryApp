package com.padcmyanmar.ttm.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class ImageLinksVO(

    @SerializedName("smallThumbnail")
    var smallThumbnail:String?,
    @SerializedName("thumbnail")
    var thumbnail:String?

)
