package com.padcmyanmar.ttm.thelibraryapp.adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemContextualMenuDelegate
import kotlinx.android.synthetic.main.view_holder_read_books_list.view.*

/*class BooksListCarouselAdapter :  CardSliderAdapter<BooksListCarouselViewHolder>() {

    override fun getItemCount(): Int {
        return 3
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksListCarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_books_list, parent, false)
        return BooksListCarouselViewHolder(view)
    }

    override fun bindVH(holder: BooksListCarouselViewHolder, position: Int) {

    }



}*/

class ReadBooksListCarouselAdapter(var bookItemContextualMenuDelegate:BookItemContextualMenuDelegate) :  CarouselAdapter() {
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_read_books_list, parent, false)
        return ReadBooksListCarouselViewHolder(bookItemContextualMenuDelegate,view)
    }


    inner class ReadBooksListCarouselViewHolder(var bookItemContextualMenuDelegate:BookItemContextualMenuDelegate,itemView: View):CarouselViewHolder(itemView){
        init {
            itemView.ivContextualMenuForReadBook.setOnClickListener {
                bookItemContextualMenuDelegate.callBottomSheetDialogFun()
            }
          //  title.setOnClickListener { onClick?.click(getItems()[adapterPosition] as SampleModel) }
        }
    }


}


