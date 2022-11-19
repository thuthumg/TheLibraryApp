package com.padcmyanmar.ttm.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName

data class EachCategoryBooksListVO(


    @SerializedName("list_name")
    val listName:String?,

    @SerializedName("display_name")
    val displayName:String?,

    @SerializedName("bestsellers_date")
    val bestsellersDate:String?,

    @SerializedName("published_date")
    val publishedDate:String?,

    @SerializedName("book_details")
    val bookDetails:List<BooksListVO>?



)
