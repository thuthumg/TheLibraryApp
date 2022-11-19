package com.padcmyanmar.ttm.thelibraryapp.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.EachCategoryView

class EachCategoryBookListPresenterImpl:ViewModel(),EachCategoryBookListPresenter {

   //View
   private var mView:EachCategoryView ?= null

    //Model
    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl
    private var mBookListVOOfflineData: List<BooksListVO>? = listOf()

  //  private var mBookListVOOnlineData: List<BooksListVO>? = listOf()


    override fun initView(view: EachCategoryView) {
        mView = view
    }

    override fun onUiReadyWithListName(
        owner: LifecycleOwner,
        listName: String
    ) {

        mTheLibraryAppModel.getReadBooksList {
            mView?.showError(it)
        }?.observe(owner){
            mBookListVOOfflineData = it
        }


       mTheLibraryAppModel.getList(
           listName = listName,
           {
              // mBookListVOOnlineData = it
            mView?.showEachCategoryBookListData(it)
           },{
               mView?.showError(it)
           })

    }


    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun callContextualMenuBottomSheetDialogFun(mBooksListVO: BooksListVO) {
        mView?.navigateToContextualMenuBottomSheetDialog(mBooksListVO)

    }

    override fun callMoreFunc(listName: String?, listId: Int?) {

    }

    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {

        saveReadBookFunc(mBooksListVO)


        mView?.navigateToBookDetailPage(mBooksListVO)
    }

    private fun saveReadBookFunc(mBooksListVO: BooksListVO?) {

        Log.d("eachcategory","presenter 1 ${mBooksListVO?.title}")

        mBooksListVO?.let { paramBookVO->
            var insertFlag:Boolean = false
            if(mBookListVOOfflineData?.size != 0)
            {

                outerLoop@ for (i in mBookListVOOfflineData?.indices!!){
                    Log.d("eachcategory","presenter 2 ${mBooksListVO?.categoryName}  ${mBookListVOOfflineData!![i].categoryName}")
                    if(mBookListVOOfflineData!![i].categoryId == mBooksListVO.categoryId &&
                        mBookListVOOfflineData!![i].title == mBooksListVO.title &&
                        mBookListVOOfflineData!![i].author == mBooksListVO.author)
                    {
                        insertFlag = false
                        break@outerLoop

                    }else{

                        insertFlag = true
                    }
                }
                Log.d("eachcategory","presenter 2 ${paramBookVO?.categoryName}")
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

    }

    override fun addToShelvesList(mBooksListVO: BooksListVO) {
        mView?.navigateToAddToShelvesList(mBooksListVO)
    }

    override fun deleteFromLibrary(id: Int) {
        mTheLibraryAppModel.deleteFromLibrary(id, onSuccess = {
            mView?.showError("successfully deleted!")
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun aboutThisBook() {

    }
}