package com.padcmyanmar.ttm.thelibraryapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.thelibraryapp.adapters.EBooksListAdapter
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_pod_ebook_list.view.*


class EBooksListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs){

    private lateinit var mEBooksListAdapter: EBooksListAdapter
    lateinit var mDelegate: BookItemDelegate
    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    private fun setUpEBooksListAdapter(delegate: BookItemDelegate) {
        mEBooksListAdapter = EBooksListAdapter(delegate)
        rvEBooksItemList.adapter = mEBooksListAdapter
        rvEBooksItemList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )

        rvEBooksItemList.isNestedScrollingEnabled = false

    }

    fun setData(delegate: BookItemDelegate, audioOrEbooks:Boolean){
        setDelegate(delegate)
        setUpEBooksListAdapter( this.mDelegate )
        mEBooksListAdapter.checkAudioOrEbooks(audioOrEbooks)
    }
    private fun setDelegate(delegate: BookItemDelegate) {
        this.mDelegate = delegate
    }

}