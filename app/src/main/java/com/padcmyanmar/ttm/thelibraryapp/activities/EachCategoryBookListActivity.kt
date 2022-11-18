package com.padcmyanmar.ttm.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.EachCategoryBooksListAdapter
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.BookListMoreBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.activity_each_category_book_list.*


class EachCategoryBookListActivity : AppCompatActivity() , BookItemDelegate {

   lateinit var mEachCategoryBooksListAdapter: EachCategoryBooksListAdapter
   lateinit var mCategoryBooksListVO: CategoryBooksListVO
    companion object {
        private const val CATEGORY_BOOKS_LIST_ID = "CATEGORY_BOOKS_LIST_ID"

        fun newIntent(context: Context, categoryBooksListVO: CategoryBooksListVO?): Intent {
            val intent = Intent(context, EachCategoryBookListActivity::class.java)
            intent.putExtra(CATEGORY_BOOKS_LIST_ID,categoryBooksListVO)
            return intent
        }
    }


   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_each_category_book_list)

       getIntentParam()
       setUpDetailPageTitle()
        setUpEBooksListAdapter()
        clickListener()
    }
    private fun setUpDetailPageTitle() {
        tvTitle.text = mCategoryBooksListVO?.displayName
    }

    private fun getIntentParam(){
        val intent = intent
        mCategoryBooksListVO = intent.getSerializableExtra(
            CATEGORY_BOOKS_LIST_ID
        ) as CategoryBooksListVO

    }
    private fun clickListener() {
        btnBackBookListMore.setOnClickListener {
            finish()
        }
    }

    private fun setUpEBooksListAdapter() {

        rvBooksItemList.isNestedScrollingEnabled = false

        mEachCategoryBooksListAdapter = EachCategoryBooksListAdapter(this)
        rvBooksItemList.adapter = mEachCategoryBooksListAdapter
        rvBooksItemList.layoutManager = GridLayoutManager(this,2,
            VERTICAL,false)
        mCategoryBooksListVO.books?.let { mEachCategoryBooksListAdapter.setData(it) }


    }

    override fun callContextualMenuBottomSheetDialogFun(mBooksListVO: BooksListVO) {
        val bookListMoreBottomSheetDialogFragment = BookListMoreBottomSheetDialogFragment()
        bookListMoreBottomSheetDialogFragment.show(supportFragmentManager,"modalSheetDialog")

    }

    override fun callMoreFunc() {

    }

    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {
        startActivity(Intent(this,BooksAndAudioDetailViewActivity::class.java))
    }

    override fun addToShelvesList(mBooksListVO: BooksListVO) {
        //
    }

    override fun deleteFromLibrary() {
        TODO("Not yet implemented")
    }

    override fun aboutThisBook() {
        TODO("Not yet implemented")
    }
}