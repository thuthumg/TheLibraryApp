package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.HomeView

class HomePresenterImpl : ViewModel(),HomePresenter {

    //View
    var mView: HomeView?= null

    //Model
    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl

    //States
    private var mCategoryBooksList: List<CategoryBooksListVO>? = listOf()

    private var mBookListVOList: List<BooksListVO>? = listOf()
    override fun initView(view: HomeView) {
       mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

        mTheLibraryAppModel.getCategoryBooksList(
            published_date = "2022-11-14",
            onSuccess = {
            mCategoryBooksList = it
            mView?.showCategoryBooksList(it)
        },
        onFailure = {
            mView?.showError(it)
        })


        mTheLibraryAppModel.getReadBooksList {
            mView?.showError(it)
        }?.observe(owner){
            mBookListVOList = it
            mView?.showReadBooksList(it.reversed())
        }

    }

    override fun callContextualMenuBottomSheetDialogFun(mBooksListVO: BooksListVO) {
       mView?.navigateToContextualMenuBottomSheetDialog(mBooksListVO)
    }

    override fun callMoreFunc(listName: String?, listId: Int?) {
        listName?.let { listId?.let { it1 -> mView?.navigateToBooksMorePage(it, it1) } }
    }

    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {

        mBooksListVO?.let { paramBookVO->
            var insertFlag:Boolean = false
            if(mBookListVOList?.size != 0)
            {

                outerLoop@ for (i in mBookListVOList?.indices!!){
                    if(mBookListVOList!![i].categoryId == mBooksListVO.categoryId &&
                        mBookListVOList!![i].title == mBooksListVO.title &&
                        mBookListVOList!![i].author == mBooksListVO.author)
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
                        paramBookVO)
                }

            }else{
                mTheLibraryAppModel.insertReadBook(
                    paramBookVO)
            }



        }

       mView?.navigateToBookDetailPage(mBooksListVO)
    }

    override fun addToShelvesList(mBooksListVO: BooksListVO) {
        mView?.navigateToAddToShelvesList(mBooksListVO)
    }

    override fun deleteFromLibrary(id: Int) {
       //
        mTheLibraryAppModel.deleteFromLibrary(id, onSuccess = {
               mView?.showError("successfully deleted!")
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun aboutThisBook() {
      //
    }

//    ,
//                onSuccess = {
//                    mView?.showError(it)
//            },
//                onFailure = {
//                    mView?.showError(it)
//                }
}