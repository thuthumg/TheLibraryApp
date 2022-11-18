package com.padcmyanmar.ttm.thelibraryapp.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.adapters.BooksCoverImageListAdapter
import com.padcmyanmar.ttm.thelibraryapp.views.component.OverlapDecoration
import kotlinx.android.synthetic.main.view_pod_overlap_recyclerview.view.*

class OverlapRecyclerviewViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs) {

    private lateinit var mBbooksCoverImageListAdapter:BooksCoverImageListAdapter

    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpAdapter()
    }

    private fun setUpAdapter() {
        mBbooksCoverImageListAdapter = BooksCoverImageListAdapter()

        rvBookCoverImage.addItemDecoration(OverlapDecoration())
        val  layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rvBookCoverImage.layoutManager = layoutManager

//        val layoutManager = LinearLayoutManager(context)
//        layoutManager.reverseLayout = true
//        layoutManager.stackFromEnd = true
//        rvBookCoverImage.layoutManager = layoutManager
//        rvBookCoverImage.addItemDecoration(OverlapDecoration(-200))


        rvBookCoverImage.adapter = mBbooksCoverImageListAdapter
        rvBookCoverImage.isNestedScrollingEnabled = false
    }

    fun setData(imgListArray: ArrayList<String>) {
        mBbooksCoverImageListAdapter.setData(imgListArray)
    }
}