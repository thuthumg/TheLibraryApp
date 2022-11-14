package com.padcmyanmar.ttm.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO

interface HomePageModel {

    fun getCategoryBooksList(
        published_date:String,
        onSuccess: (List<CategoryBooksListVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun insertReadBook(
        readBook:BooksListVO,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getReadBooksList(
        onFailure: (String) -> Unit
    ):LiveData<List<BooksListVO>>?
}