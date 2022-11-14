package com.padcmyanmar.ttm.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class CategoryBooksVO(

    @SerializedName("bestsellers_date")
    val bestsellersDate:String?,

    @SerializedName("published_date")
    val publishedDate:String?,

    @SerializedName("published_date_description")
    val publishedDateDescription:String?,

    @SerializedName("previous_published_date")
    val previousPublishedDate:String?,

    @SerializedName("next_published_date")
    val nextPublishedDate:String?,

    @SerializedName("lists")
    val lists:List<CategoryBooksListVO>?,

)
