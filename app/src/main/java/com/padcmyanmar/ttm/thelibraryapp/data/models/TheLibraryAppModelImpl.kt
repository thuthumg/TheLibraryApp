package com.padcmyanmar.ttm.thelibraryapp.data.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object TheLibraryAppModelImpl:BaseModel(),TheLibraryAppModel {

    /*For Home Page Section*/
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
        readBook: BooksListVO
    ) {
        //save data to table
        mTheLibraryAppDatabase?.readBooksDao()?.insertReadBook(readBook)
    }

    /*For Home Carousel and Library Section*/
    override fun getReadBooksList(onFailure: (String) -> Unit
    ): LiveData<List<BooksListVO>>? {

        return mTheLibraryAppDatabase?.readBooksDao()?.getAllReadBooks()
    }


    override fun deleteFromLibrary(
        bookId: Int,onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {

        mTheLibraryAppDatabase?.readBooksDao()?.deleteReadBooksById(bookId)
        onSuccess("success")
    }

    /*For Shelf Section*/
    override fun insertShelf(
        shelfVO: ShelfVO,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mTheLibraryAppDatabase?.shelvesDao()?.insertShelves(
            shelfVO
        )
        onSuccess("success")

        //  onFailure("fail")


    }

    override fun insertAllShelf(
        shelfList: List<ShelfVO>,
        onSuccess: (message: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheLibraryAppDatabase?.shelvesDao()?.insertAllShelves(
            shelfList
        )
        onSuccess("success")
    }

    override fun getShelvesList(onFailure: (String) -> Unit): LiveData<List<ShelfVO>>? {
        return mTheLibraryAppDatabase?.shelvesDao()?.getAllShelves()
    }

//    override fun getShelvesList(onSuccess: (shelvesList:List<ShelfVO>) -> Unit, onFailure: (String) -> Unit) {
//
//        onSuccess(
//            mTheLibraryAppDatabase?.shelvesDao()?.getAllShelves(
//            ) ?: listOf()
//        )
//
//    }

    override fun deleteShelf(
        shelvesId: Int,onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {

        mTheLibraryAppDatabase?.shelvesDao()?.deleteByShelfId(shelvesId)
        onSuccess("success")
    }

    override fun getList(
        listName: String,
        onSuccess: (List<BooksListVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        var booksListVOList:ArrayList<BooksListVO> = arrayListOf()

        //network
        mTheLibraryApi.getLists(list = listName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({listJsonResponse->
                listJsonResponse.results?.let {


                    it.forEach { eachCategoryBooksListVO ->
                        eachCategoryBooksListVO.bookDetails?.forEach {booksListVO->
                            booksListVOList.add(booksListVO)
                        }
                    }

                onSuccess(booksListVOList)
                 }
            },
                {
                    onFailure(it.localizedMessage ?: "")
                })
    }

    override fun filterByCategory(categoryName:String): List<BooksListVO> {

     return   mTheLibraryAppDatabase?.readBooksDao()?.getReadBooksByCategory(categoryName)?: listOf()
    }

    override fun getAllBooksListNoSelectedCategory(): List<BooksListVO> {
        return mTheLibraryAppDatabase?.readBooksDao()?.getReadBooksNoSelectedCategory() ?: listOf()
    }
}