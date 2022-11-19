package com.padcmyanmar.ttm.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.EachCategoryBooksListAdapter
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.BookItemBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.BookListMoreBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.EachCategoryBookListPresenter
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.EachCategoryBookListPresenterImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.ShelvesListDetailPresenterImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.EachCategoryView
import kotlinx.android.synthetic.main.activity_each_category_book_list.*


class EachCategoryBookListActivity : AppCompatActivity() ,EachCategoryView  {

    //mPresenter
    private lateinit var mPresenter: EachCategoryBookListPresenter


   lateinit var mEachCategoryBooksListAdapter: EachCategoryBooksListAdapter
  // lateinit var mCategoryBooksListVO: CategoryBooksListVO
   var mCategoryName:String = ""
    var mCategoryId : Int? = null
    companion object {
        private const val CATEGORY_BOOKS_LIST_NAME = "CATEGORY_BOOKS_LIST_NAME"
        private const val CATEGORY_BOOKS_LIST_ID = "CATEGORY_BOOKS_LIST_ID"
        fun newIntent(context: Context, categoryName: String?,categoryId: Int?): Intent {
            val intent = Intent(context, EachCategoryBookListActivity::class.java)
            intent.putExtra(CATEGORY_BOOKS_LIST_NAME,categoryName)
            intent.putExtra(CATEGORY_BOOKS_LIST_ID,categoryId)
            return intent
        }
    }


   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_each_category_book_list)
        setUpPresenter()
       getIntentParam()
       setUpDetailPageTitle()
        setUpEBooksListAdapter()
        clickListener()


    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[EachCategoryBookListPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpDetailPageTitle() {
        tvTitle.text = mCategoryName
    }

    private fun getIntentParam(){
        val intent = intent
        mCategoryName = intent.getStringExtra(CATEGORY_BOOKS_LIST_NAME).toString()
        mCategoryId = intent.getIntExtra(CATEGORY_BOOKS_LIST_ID,0)

        mPresenter.onUiReadyWithListName(this,mCategoryName)
    }
    private fun clickListener() {
        btnBackBookListMore.setOnClickListener {
            finish()
        }
    }

    private fun setUpEBooksListAdapter() {



        mEachCategoryBooksListAdapter = EachCategoryBooksListAdapter(mPresenter)
        rvBooksItemList.adapter = mEachCategoryBooksListAdapter
        rvBooksItemList.layoutManager = GridLayoutManager(this,2,
            VERTICAL,false)

        rvBooksItemList.isNestedScrollingEnabled = false

    }
//
//    override fun callContextualMenuBottomSheetDialogFun(mBooksListVO: BooksListVO) {
//        val bookListMoreBottomSheetDialogFragment = BookListMoreBottomSheetDialogFragment()
//        bookListMoreBottomSheetDialogFragment.show(supportFragmentManager,"modalSheetDialog")
//
//    }
//
//    override fun callMoreFunc() {
//
//    }
//
//    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {
//        startActivity(Intent(this,BooksAndAudioDetailViewActivity::class.java))
//    }
//
//    override fun addToShelvesList(mBooksListVO: BooksListVO) {
//        //
//    }
//
//    override fun deleteFromLibrary(id: Int) {
//
//    }
//
//    override fun aboutThisBook() {
//
//    }

    override fun showEachCategoryBookListData(booksListVOList: List<BooksListVO>) {


        booksListVOList?.let {

            booksListVOList?.forEach { booksListVO ->
                booksListVO.categoryId = mCategoryId
                booksListVO.categoryName = mCategoryName
            }

            mEachCategoryBooksListAdapter.setData(it)
        }
    }

    override fun navigateToContextualMenuBottomSheetDialog(mBooksListVO: BooksListVO) {
        val customButtonSheet = BookItemBottomSheetDialogFragment(mPresenter)
        var args: Bundle = Bundle()
        args.putSerializable("booksItemParamData", mBooksListVO)
        customButtonSheet.arguments = args
        customButtonSheet.show(supportFragmentManager,"modalSheetDialog")

    }

    override fun navigateToBookDetailPage(mBooksListVO: BooksListVO?) {
            Log.d("eachcategory","check book data = "+mBooksListVO?.title)
           mBooksListVO?.let { booksListVO ->
                startActivity(BooksAndAudioDetailViewActivity.newIntent(this,booksListVO))
            }

    }

    override fun navigateToAddToShelvesList(mBooksListVO: BooksListVO) {
        startActivity( AddToShelvesActivity.newIntent(this, mBooksListVO))

    }

    override fun showError(errorString: String) {
        Toast.makeText(this,errorString,Toast.LENGTH_SHORT).show()
    }
}