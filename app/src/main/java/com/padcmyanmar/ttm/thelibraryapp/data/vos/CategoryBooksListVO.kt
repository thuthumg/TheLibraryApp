package com.padcmyanmar.ttm.thelibraryapp.data.vos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryBooksListVO(

    @SerializedName("list_id")
    val listId: Int?,

    @SerializedName("list_name")
    val listName: String?,

    @SerializedName("list_name_encoded")
    val listNameEncoded: String?,

    @SerializedName("display_name")
    val displayName: String?,

    @SerializedName("updated")
    val updated: String?,

//    val list_image: String?,
//    val list_image_width: String?,
//    val list_image_height: String?,

    @SerializedName("books")
    val books: List<BooksListVO>?

): Serializable
