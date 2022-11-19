package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.GoogleBookItemDelegate
import kotlinx.android.synthetic.main.view_holder_book.view.*
import kotlinx.android.synthetic.main.view_holder_google_play_book.view.*
import kotlinx.android.synthetic.main.view_holder_google_play_book.view.ivBookCoverPhoto
import kotlinx.android.synthetic.main.view_holder_google_play_book.view.tvAuthor
import kotlinx.android.synthetic.main.view_holder_google_play_book.view.tvBookTitle

class GooglePlayBookViewHolder  (var googleBookItemDelegate: GoogleBookItemDelegate,itemView: View) : RecyclerView.ViewHolder(itemView){

    lateinit var mBooksListVO: BooksListVO

    init {
        itemView.llGoogleBookItem.setOnClickListener {
            googleBookItemDelegate.goToDetailPage(mBooksListVO)
        }
    }

    fun bindData(booksListVO: BooksListVO) {


        mBooksListVO = booksListVO

        Log.d("viewholder","check image = ${booksListVO.bookImage}")
        itemView.tvBookTitle.text = booksListVO.title
        itemView.tvAuthor.text = booksListVO.author

        Glide.with(itemView.context)
            .load(booksListVO.bookImage)
            .placeholder(R.drawable.empty_book_icon)
            .override(60,80)
            .into(itemView.ivBookCoverPhoto)


    }
}