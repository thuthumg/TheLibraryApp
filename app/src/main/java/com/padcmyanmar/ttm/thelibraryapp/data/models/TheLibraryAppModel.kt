package com.padcmyanmar.ttm.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.network.responses.ListJsonResponse
import io.reactivex.rxjava3.core.Observable

interface TheLibraryAppModel {

    /*For Home Page Section*/
    fun getCategoryBooksList(
        published_date: String,
        onSuccess: (List<CategoryBooksListVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun insertReadBook(
        readBook: BooksListVO
    )

    /*For Home Carousel and Library Section*/
    fun getReadBooksList(
        onFailure: (String) -> Unit
    ): LiveData<List<BooksListVO>>?

    fun deleteFromLibrary(
        bookId: Int,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )


    /*For Library Section*/
    fun filterByCategory(
        categoryName: String
    ): List<BooksListVO>

    fun getAllBooksListNoSelectedCategory(): List<BooksListVO>

    /*For Shelf Section*/
    fun insertShelf(
        shelfName: ShelfVO,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun insertAllShelf(
        shelfList: List<ShelfVO>,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )

    /*  fun getShelvesList(
          onSuccess: (shelvesList:List<ShelfVO>) -> Unit,
          onFailure: (String) -> Unit
      )*/

    fun getShelvesList(
        onFailure: (String) -> Unit
    ): LiveData<List<ShelfVO>>?

    fun deleteShelf(
        shelvesId: Int,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    )

    /*For Each Category book list section*/
    fun getList(
        listName: String,
        onSuccess: (List<BooksListVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    /*for search book list*/
    fun getGoogleBooksList(
        query: String
    ): Observable<List<BooksListVO>>?

}