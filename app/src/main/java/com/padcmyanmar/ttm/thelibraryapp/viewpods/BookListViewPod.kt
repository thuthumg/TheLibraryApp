package com.padcmyanmar.ttm.thelibraryapp.viewpods

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.BookListAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.ViewTypeListBookListAdapter
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_pod_book_list.view.*

class BookListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var mBookListAdapter: BookListAdapter
    lateinit var mViewTypeListBookListAdapter:ViewTypeListBookListAdapter
    lateinit var mDelegate: BookItemDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
    }


    fun setData(delegate: BookItemDelegate, audioOrEbooks:Boolean, changeListViewFlag:String){
        setDelegate(delegate)
        setUpBooksListAdapter(this.mDelegate,audioOrEbooks,changeListViewFlag)
    }
    private fun setDelegate(delegate: BookItemDelegate) {
        this.mDelegate = delegate
    }


    @SuppressLint("RestrictedApi")
    private fun setUpBooksListAdapter(delegate: BookItemDelegate,
                                      checkAudioOrEbooksFlagParam: Boolean, listViewType:String) {


        when (listViewType) {
           context.getString(R.string.lbl_large_grid_list) -> {
                largeGridViewUI(delegate,checkAudioOrEbooksFlagParam)

            }
            context.getString(R.string.lbl_small_grid_list) -> {
                smallGridViewUI(delegate,checkAudioOrEbooksFlagParam)

            }
            else -> {
                listViewUI(delegate,checkAudioOrEbooksFlagParam)
            }
        }






    }

    private fun listViewUI(
        delegate: BookItemDelegate,
        checkAudioOrEbooksFlagParam: Boolean
    ) {
        mViewTypeListBookListAdapter = ViewTypeListBookListAdapter(delegate,checkAudioOrEbooksFlagParam)
        rvBookList.adapter = mViewTypeListBookListAdapter
        rvBookList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false)
        rvBookList.isNestedScrollingEnabled = false
    }

    private fun smallGridViewUI(
        delegate: BookItemDelegate,
        checkAudioOrEbooksFlagParam: Boolean
    ) {
        mBookListAdapter = BookListAdapter(delegate,checkAudioOrEbooksFlagParam)
        rvBookList.adapter = mBookListAdapter
        rvBookList.layoutManager = GridLayoutManager(
            context,
            3,
            LinearLayoutManager.VERTICAL, false)
        rvBookList.isNestedScrollingEnabled = false
    }

    private fun largeGridViewUI(
        delegate: BookItemDelegate,
        checkAudioOrEbooksFlagParam: Boolean
    ) {

        mBookListAdapter = BookListAdapter(delegate,checkAudioOrEbooksFlagParam)
        rvBookList.adapter = mBookListAdapter
        rvBookList.layoutManager = GridLayoutManager(
            context,
            2,
            LinearLayoutManager.VERTICAL, false)
        rvBookList.isNestedScrollingEnabled = false
    }
}


