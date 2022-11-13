package com.padcmyanmar.ttm.thelibraryapp.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "shelves")
data class ShelfVO (

    @SerializedName("id")
    @PrimaryKey
    val id: Int?,
    @SerializedName("shelf_name")
    val shelfName: String?



)