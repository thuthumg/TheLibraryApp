package com.padcmyanmar.ttm.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class VolumeInfoVO(

    @SerializedName("title")
    var title: String?,
    @SerializedName("subtitle")
    var subtitle: String?,
    @SerializedName("authors")
    var authors: List<String>?,
    @SerializedName("publisher")
    var publisher: String?,
    @SerializedName("publishedDate")
    var publishedDate: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("categories")
    var categories: List<String>?,
    @SerializedName("imageLinks")
    var imageLinks: ImageLinksVO?

)