package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.ViewTypeListBookListViewHolder


class ViewTypeListBookListAdapter (
    var bookItemDelegate: BookItemDelegate,
    var checkAudioOrEbooksFlagParam: Boolean) : RecyclerView.Adapter<ViewTypeListBookListViewHolder>(){

    private var checkAudioOrEbooksFlag:Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTypeListBookListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_list_view_type_book,
                parent,false)

        return ViewTypeListBookListViewHolder(bookItemDelegate = bookItemDelegate,
            view)
    }

    override fun onBindViewHolder(holder: ViewTypeListBookListViewHolder, position: Int) {
        checkAudioOrEbooksFlag = checkAudioOrEbooksFlagParam
        holder.bindData(checkAudioOrEbooksFlag)
    }

    override fun getItemCount(): Int {
        return 10
    }
}