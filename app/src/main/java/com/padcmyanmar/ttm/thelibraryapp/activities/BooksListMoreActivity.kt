package com.padcmyanmar.ttm.thelibraryapp.activities

import android.content.Intent
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.EBooksListAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.UnReadBooksAdapter
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemContextualMenuDelegate
import kotlinx.android.synthetic.main.activity_books_list_more.*
import kotlinx.android.synthetic.main.view_holder_ebooks_list.view.*
import kotlinx.android.synthetic.main.view_pod_ebook_list.view.*

class BooksListMoreActivity : AppCompatActivity() , BookItemContextualMenuDelegate {

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

    override fun callBottomSheetDialogFun() {

    }

    override fun callMoreFunc() {

    }
}