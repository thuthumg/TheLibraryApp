package com.padcmyanmar.ttm.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object HomePageModelImpl : BaseModel(), HomePageModel {

    override fun getCategoryBooksList(
        published_date: String,
        onSuccess: (List<CategoryBooksListVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {


        //Network
        mTheLibraryApi.getOverviewBooksList(publishedDate = published_date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.lists?.let { it1 -> onSuccess(it1) }
            },
                {
                    onFailure(it.localizedMessage ?: "")
                })
    }

    override fun insertReadBook(
        readBook: BooksListVO,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //save data to table
        mTheLibraryAppDatabase?.readBooksDao()?.insertReadBook(readBook)


    }

    override fun getReadBooksList(onFailure: (String) -> Unit
    ): LiveData<List<BooksListVO>>? {
        return mTheLibraryAppDatabase?.readBooksDao()?.getAllReadBooks()
    }


}