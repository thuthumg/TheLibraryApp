package com.padcmyanmar.ttm.thelibraryapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

@Dao
interface ReadBooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReadBook(booksListVO: BooksListVO?)

    @Query("SELECT * FROM books")
    fun getAllReadBooks(): LiveData<List<BooksListVO>>?

}