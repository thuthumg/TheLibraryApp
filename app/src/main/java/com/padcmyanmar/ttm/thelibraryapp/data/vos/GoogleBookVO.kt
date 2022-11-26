package com.padcmyanmar.ttm.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class GoogleBookVO(
    @SerializedName("id")
    var id:String?,
    @SerializedName("volumeInfo")
    var volumeInfo:VolumeInfoVO?
){
    fun convertToBookVO(position: Int): BooksListVO {


        return BooksListVO(
            position,
            position,
            volumeInfo?.categories?.joinToString(","),
            volumeInfo?.authors?.joinToString(","),
            volumeInfo?.imageLinks?.smallThumbnail,
            60,
            80,
            "",
            "",
            "",
            volumeInfo?.publishedDate+" 00:00:00",
            volumeInfo?.description,
            "0.0",
            "",
            volumeInfo?.publisher,
            volumeInfo?.title,
            volumeInfo?.publishedDate +" 00:00:00"
        )



    }
}
