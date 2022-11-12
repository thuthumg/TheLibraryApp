package com.padcmyanmar.ttm.thelibraryapp.adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_holder_read_books_list.view.*

class ReadBooksListCarouselAdapter(var bookItemDelegate:BookItemDelegate) :  CarouselAdapter() {
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_read_books_list, parent, false)
        return ReadBooksListCarouselViewHolder(bookItemDelegate,view)
    }


    inner class ReadBooksListCarouselViewHolder(var bookItemDelegate:BookItemDelegate, itemView: View):CarouselViewHolder(itemView){
        init {
            itemView.ivContextualMenuForReadBook.setOnClickListener {
                bookItemDelegate.callContextualMenuBottomSheetDialogFun()
            }

        }
    }


}


