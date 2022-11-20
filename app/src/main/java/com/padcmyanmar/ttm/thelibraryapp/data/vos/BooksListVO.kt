package com.padcmyanmar.ttm.thelibraryapp.data.vos

import alirezat775.lib.carouselview.CarouselModel
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "books")
data class BooksListVO (

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "category_id")
    var categoryId: Int?,

    @ColumnInfo(name = "category_name")
    var categoryName: String?,

//    @SerializedName("age_group")
//    @ColumnInfo(name = "age_group")
//    val ageGroup:String?,
//
//    @SerializedName("amazon_product_url")
//    @ColumnInfo(name = "amazon_product_url")
//    val amazonProductUrl:String?,
//
//    @SerializedName("article_chapter_link")
//    @ColumnInfo(name = "article_chapter_link")
//    val articleChapterLink:String?,

    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author:String?,

    @SerializedName("book_image")
    @ColumnInfo(name = "book_image")
    val bookImage:String?,

    @SerializedName("book_image_width")
    @ColumnInfo(name = "book_image_width")
    val bookImageWidth:Int?,

    @SerializedName("book_image_height")
    @ColumnInfo(name = "book_image_height")
    val bookImageHeight:Int?,

    @SerializedName("book_review_link")
    @ColumnInfo(name = "book_review_link")
    val bookReviewLink:String?,

    @SerializedName("contributor")
    @ColumnInfo(name = "contributor")
    val contributor:String?,

    @SerializedName("contributor_note")
    @ColumnInfo(name = "contributor_note")
    val contributorNote:String?,

    @SerializedName("created_date")
    @ColumnInfo(name = "created_date")
    val createdDate:String?,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description:String?,

  /*  @SerializedName("first_chapter_link")
    @ColumnInfo(name = "first_chapter_link")
    val firstChapterLink:String?,*/

    @SerializedName("price")
    @ColumnInfo(name = "price")
    val price:String?,

    @SerializedName("book_uri")
    @ColumnInfo(name = "book_uri")
    val bookUri:String?,

    @SerializedName("publisher")
    @ColumnInfo(name = "publisher")
    val publisher:String?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title:String?,

    @SerializedName("updated_date")
    @ColumnInfo(name = "updated_date")
    val updatedDate:String?


)  : Serializable , CarouselModel(){



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BooksListVO

        if (id != other.id) return false

        return true
    }

}
