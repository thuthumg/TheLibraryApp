package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_holder_each_category_book_list.view.*

class EachCategoryBooksListViewHolder(itemView: View, bookItemDelegate: BookItemDelegate) : RecyclerView.ViewHolder(itemView){


    init {

        itemView.ivContextualMenu.setOnClickListener {
            bookItemDelegate.callContextualMenuBottomSheetDialogFun()

        }
        itemView.ivBookCoverPhoto.setOnClickListener {
           // bookItemDelegate.callBookDetailPage(mBooksListVO)
        }

    }

}