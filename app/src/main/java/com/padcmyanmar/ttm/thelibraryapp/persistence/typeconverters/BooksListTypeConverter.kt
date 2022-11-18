package com.padcmyanmar.ttm.thelibraryapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

class BooksListTypeConverter {

    @TypeConverter
    fun toString(booksList: List<BooksListVO>?):String{
        return Gson().toJson(booksList)
    }

    @TypeConverter
    fun toBooksList(booksListJsonStr: String): List<BooksListVO>?{
       var booksListType = object : TypeToken<List<BooksListVO>?>(){}.type
        return Gson().fromJson(booksListJsonStr,booksListType)
    }
}