package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.BookListViewHolder

class BookListAdapter(
    var bookItemDelegate: BookItemDelegate,
    var checkAudioOrEbooksFlagParam: Boolean) : RecyclerView.Adapter<BookListViewHolder>(){

    private var checkAudioOrEbooksFlag:Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_book,
                parent,false)

        return BookListViewHolder(bookItemDelegate = bookItemDelegate,
        view)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        checkAudioOrEbooksFlag = checkAudioOrEbooksFlagParam
        holder.bindData(checkAudioOrEbooksFlag)
    }

    override fun getItemCount(): Int {
        return 10
    }
}