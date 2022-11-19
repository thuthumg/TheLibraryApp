package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.thelibraryapp.R
import kotlinx.android.synthetic.main.view_holder_book_cover_list.view.*


class BooksCoverImageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(s: String) {

        Glide.with(itemView.context)
            .load(s)
            .placeholder(R.drawable.empty_shelf_book_bg)
            .override(40, 80)
            .into(itemView.ivBookCoverPhotoInShelf)

    }
}