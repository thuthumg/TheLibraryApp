package com.padcmyanmar.ttm.thelibraryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.EachCategoryBooksListAdapter
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.BookListMoreBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.activity_each_category_book_list.*


class EachCategoryBookListActivity : AppCompatActivity() , BookItemDelegate {

   lateinit var mEachCategoryBooksListAdapter: EachCategoryBooksListAdapter

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_each_category_book_list)
        setUpEBooksListAdapter()
        clickListener()
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

    }

    override fun callContextualMenuBottomSheetDialogFun() {
        val bookListMoreBottomSheetDialogFragment = BookListMoreBottomSheetDialogFragment()
        bookListMoreBottomSheetDialogFragment.show(supportFragmentManager,"modalSheetDialog")

    }

    override fun callMoreFunc() {

    }

    override fun callBookDetailPage() {
        startActivity(Intent(this,BooksAndAudioDetailViewActivity::class.java))
    }
}