package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.adapters.UnReadBooksAdapter
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_holder_ebooks_list.view.*


class EBooksListViewHolder(var bookItemDelegate: BookItemDelegate, itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var mUnReadBooksAdapter: UnReadBooksAdapter
    private var checkAudioOrEbooksFlag:Boolean = false

    init {
        itemView.llMore.setOnClickListener{
            bookItemDelegate.callMoreFunc()
        }
    }

    fun bindData(checkAudioOrEbookFlagParam:Boolean){
        checkAudioOrEbooksFlag = checkAudioOrEbookFlagParam
        setUpEBookItemRecyclerView(checkAudioOrEbooksFlag)
    }

    private fun setUpEBookItemRecyclerView(checkAudioOrEbooksFlag: Boolean) {
        mUnReadBooksAdapter = UnReadBooksAdapter(bookItemDelegate,checkAudioOrEbooksFlag)
        itemView.rvUnReadBooksItemList.adapter = mUnReadBooksAdapter
        itemView.rvUnReadBooksItemList.layoutManager = LinearLayoutManager(
            itemView.context,
            LinearLayoutManager.HORIZONTAL, false
        )
    }
}