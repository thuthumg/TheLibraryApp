package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.app.ActionBar.LayoutParams
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemContextualMenuDelegate
import com.padcmyanmar.ttm.thelibraryapp.fragments.BookItemBottomSheetDialogFragment
import kotlinx.android.synthetic.main.view_holder_ebooks_list.view.*

import kotlinx.android.synthetic.main.view_holder_unread_books_list.view.*

class UnReadBooksViewHolder(var bookItemContextualMenuDelegate: BookItemContextualMenuDelegate,itemView: View) : RecyclerView.ViewHolder(itemView){

    init {
        itemView.ivContextualMenu.setOnClickListener {
              bookItemContextualMenuDelegate.callBottomSheetDialogFun()
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
