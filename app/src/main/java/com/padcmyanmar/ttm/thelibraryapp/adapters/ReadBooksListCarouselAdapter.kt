package com.padcmyanmar.ttm.thelibraryapp.adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padcmyanmar.ttm.thelibraryapp.R

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

class ReadBooksListCarouselAdapter :  CarouselAdapter() {
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_read_books_list, parent, false)
        return ReadBooksListCarouselViewHolder(view)
    }


    inner class ReadBooksListCarouselViewHolder(itemView: View):CarouselViewHolder(itemView){
        init {
          //  title.setOnClickListener { onClick?.click(getItems()[adapterPosition] as SampleModel) }
        }
    }


}


