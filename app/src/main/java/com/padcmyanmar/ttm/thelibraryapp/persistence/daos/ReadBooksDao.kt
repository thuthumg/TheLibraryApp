package com.padcmyanmar.ttm.thelibraryapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO

@Dao
interface ReadBooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReadBook(booksListVO: BooksListVO?)


    @Query("SELECT * FROM books")
    fun getAllReadBooks(): LiveData<List<BooksListVO>>?


    @Query("SELECT * FROM books where category_name like :categoryName")
    fun getReadBooksByCategory(categoryName:String): List<BooksListVO>

    @Query("SELECT * FROM books")
    fun getReadBooksNoSelectedCategory(): List<BooksListVO>


    @Query("DELETE FROM books where id = :bookId")
    fun deleteReadBooksById(bookId:Int)

}