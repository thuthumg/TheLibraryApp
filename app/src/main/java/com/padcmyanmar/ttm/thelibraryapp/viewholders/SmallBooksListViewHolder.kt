package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_holder_book.view.*

class SmallBooksListViewHolder  (var bookItemDelegate: BookItemDelegate, itemView: View) : RecyclerView.ViewHolder(itemView){

    lateinit var mBooksListVO:BooksListVO


    init {
        itemView.ivContextualMenu.setOnClickListener {
            bookItemDelegate.callContextualMenuBottomSheetDialogFun(mBooksListVO)
        }

        itemView.cvBookCover.setOnClickListener {
            //  bookItemDelegate.callBookDetailPage(mBooksListVO)
        }
    }


    fun bindData(checkAudioOrEbooksFlagParam: Boolean, booksListVO: BooksListVO){
        mBooksListVO = booksListVO
        if(checkAudioOrEbooksFlagParam)
        {
            itemView.ivAudio.visibility = View.VISIBLE
        }else{
            itemView.ivAudio.visibility = View.GONE
        }

        itemView.tvBookTitle.text = booksListVO.title
        itemView.tvAuthor.text = booksListVO.author


        booksListVO.bookImageHeight?.let { h->
            booksListVO.bookImageWidth?.let { w ->
                Glide.with(itemView.context)
                    .load(booksListVO.bookImage)
                    .override(w, h)
                    .into(itemView.ivBookCoverPhoto)
            }
        }
    }
}