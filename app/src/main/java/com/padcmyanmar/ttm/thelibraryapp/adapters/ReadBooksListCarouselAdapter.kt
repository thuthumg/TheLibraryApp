package com.padcmyanmar.ttm.thelibraryapp.adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_holder_read_books_list.view.*
import kotlinx.android.synthetic.main.view_holder_unread_books_list.view.*

class ReadBooksListCarouselAdapter(var bookItemDelegate:BookItemDelegate) :  CarouselAdapter() {
   lateinit var mBooksListVO:BooksListVO

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {

        mBooksListVO = getItems()[position] as BooksListVO


        mBooksListVO.bookImageHeight?.let {
            mBooksListVO.bookImageWidth?.let { it1 ->
                Glide.with((holder as ReadBooksListCarouselViewHolder).itemView.context)
                    .load(mBooksListVO.bookImage)
                    .placeholder(R.drawable.empty_book_icon)
                    .override(it1, it)
                    .into((holder as ReadBooksListCarouselViewHolder).itemView.ivCarouselReadBook)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_read_books_list, parent, false)
        return ReadBooksListCarouselViewHolder(bookItemDelegate,view)
    }


    inner class ReadBooksListCarouselViewHolder(var bookItemDelegate:BookItemDelegate, itemView: View):CarouselViewHolder(itemView){

        init {
            itemView.ivCarouselReadBook.setOnClickListener {
                bookItemDelegate.callBookDetailPage(getItems()[adapterPosition] as BooksListVO)
            }

            itemView.ivContextualMenuForReadBook.setOnClickListener {
              //  bookItemDelegate.callContextualMenuBottomSheetDialogFun(mBooksListVO)

                bookItemDelegate.callContextualMenuBottomSheetDialogFun(getItems()[adapterPosition] as BooksListVO)
            }
        }
    }


}


