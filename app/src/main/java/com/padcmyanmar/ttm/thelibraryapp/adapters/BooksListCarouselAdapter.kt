package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.islamkhsh.CardSliderAdapter
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.viewholders.BooksListCarouselViewHolder

class BooksListCarouselAdapter :  CardSliderAdapter<BooksListCarouselViewHolder>() {

    override fun getItemCount(): Int {
        return 3
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksListCarouselViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_books_list, parent, false)
        return BooksListCarouselViewHolder(view)
    }

    override fun bindVH(holder: BooksListCarouselViewHolder, position: Int) {

    }



}
