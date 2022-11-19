package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.SearchBookView
import io.reactivex.rxjava3.core.Observable

class SearchBookPresenterImpl : ViewModel() , SearchBookPresenter {

    //View
    var mView: SearchBookView ?= null

    //Model
    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl

    private var mBookListVOOfflineData: List<BooksListVO>? = listOf()

    override fun initView(view: SearchBookView) {
        mView = view
    }

    override fun saveReadBook(booksVO: BooksListVO) {


        mBookListVOOfflineData?.let {
            var insertFlag:Boolean = false
            if(mBookListVOOfflineData?.size != 0)
            {

                outerLoop@ for (i in mBookListVOOfflineData?.indices!!){
                    if(mBookListVOOfflineData!![i].categoryId == booksVO.categoryId &&
                        mBookListVOOfflineData!![i].title == booksVO.title &&
                        mBookListVOOfflineData!![i].author == booksVO.author)
                    {
                        insertFlag = false
                        break@outerLoop

                    }else{

                        insertFlag = true
                    }
                }

                if(insertFlag)
                {
                    mTheLibraryAppModel.insertReadBook(
                        booksVO)
                }

            }else{
                mTheLibraryAppModel.insertReadBook(
                    booksVO)
            }



        }


    }

    override fun searchGoogleBooksList(searchQueryText:String): Observable<List<BooksListVO>>?{

      return  mTheLibraryAppModel.getGoogleBooksList(searchQueryText)
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mTheLibraryAppModel.getReadBooksList {
            mView?.showError(it)
        }?.observe(owner){
            mBookListVOOfflineData = it
        }
    }

    override fun goToDetailPage(mBooksListVO: BooksListVO?) {
        mView?.navigateToBookDetailPage(mBooksListVO)
    }
}