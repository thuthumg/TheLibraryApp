package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_holder_unread_books_list.view.*

class BookListViewHolder (var bookItemDelegate: BookItemDelegate, itemView: View) : RecyclerView.ViewHolder(itemView){

    init {
        itemView.ivContextualMenu.setOnClickListener {
            bookItemDelegate.callContextualMenuBottomSheetDialogFun()
        }
    }


    fun bindData(checkAudioOrEbooksFlagParam:Boolean){

        if(checkAudioOrEbooksFlagParam)
        {
            itemView.ivAudio.visibility = View.VISIBLE
        }else{
            itemView.ivAudio.visibility = View.GONE
        }
    }
}