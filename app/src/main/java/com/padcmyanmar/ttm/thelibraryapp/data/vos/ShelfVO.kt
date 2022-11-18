package com.padcmyanmar.ttm.thelibraryapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.ttm.thelibraryapp.persistence.typeconverters.BooksListTypeConverter

@Entity(tableName = "shelves")
@TypeConverters(
    BooksListTypeConverter::class
)
data class ShelfVO(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "shelf_name")
    var shelfName: String?,

    @ColumnInfo(name = "books_list")
    var booksList: List<BooksListVO>?,


    var isSelected:Boolean = false

): java.io.Serializable