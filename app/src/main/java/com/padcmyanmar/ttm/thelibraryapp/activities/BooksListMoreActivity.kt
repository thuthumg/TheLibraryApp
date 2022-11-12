package com.padcmyanmar.ttm.thelibraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.UnReadBooksAdapter
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.activity_books_list_more.*

class BooksListMoreActivity : AppCompatActivity() , BookItemDelegate {

   lateinit var mBooksListAdapter:UnReadBooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_list_more)
        setUpEBooksListAdapter()
    }

    private fun setUpEBooksListAdapter() {

        rvBooksItemList.isNestedScrollingEnabled = false

        mBooksListAdapter = UnReadBooksAdapter(this,false)
        rvBooksItemList.adapter = mBooksListAdapter
        rvBooksItemList.layoutManager = GridLayoutManager(this,2,
            VERTICAL,false)

    }

    override fun callContextualMenuBottomSheetDialogFun() {

    }

    override fun callMoreFunc() {

    }
}