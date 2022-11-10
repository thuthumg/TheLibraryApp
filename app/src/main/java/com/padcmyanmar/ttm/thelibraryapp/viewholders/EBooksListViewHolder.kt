package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.adapters.UnReadBooksAdapter
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemContextualMenuDelegate
import kotlinx.android.synthetic.main.view_holder_ebooks_list.view.*
import kotlinx.android.synthetic.main.view_holder_unread_books_list.view.*


class EBooksListViewHolder(var bookItemContextualMenuDelegate: BookItemContextualMenuDelegate,itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var mUnReadBooksAdapter: UnReadBooksAdapter
    private var checkAudioOrEbooksFlag:Boolean = false

    init {
        itemView.llMore.setOnClickListener{
            bookItemContextualMenuDelegate.callMoreFunc()
        }
    }

    fun bindData(checkAudioOrEbookFlagParam:Boolean){
        checkAudioOrEbooksFlag = checkAudioOrEbookFlagParam
        setUpEBookItemRecyclerView(checkAudioOrEbooksFlag)
    }

    private fun setUpEBookItemRecyclerView(checkAudioOrEbooksFlag: Boolean) {
        mUnReadBooksAdapter = UnReadBooksAdapter(bookItemContextualMenuDelegate,checkAudioOrEbooksFlag)
        itemView.rvUnReadBooksItemList.adapter = mUnReadBooksAdapter
        itemView.rvUnReadBooksItemList.layoutManager = LinearLayoutManager(
            itemView.context,
            LinearLayoutManager.HORIZONTAL, false
        )
    }
}