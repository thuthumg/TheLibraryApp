package com.padcmyanmar.ttm.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class GoogleBookVO(
    @SerializedName("id")
    var id:String?,
    @SerializedName("volumeInfo")
    var volumeInfo:VolumeInfoVO?
)
