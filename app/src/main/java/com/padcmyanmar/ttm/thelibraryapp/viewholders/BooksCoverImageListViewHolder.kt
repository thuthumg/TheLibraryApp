package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.thelibraryapp.R
import kotlinx.android.synthetic.main.view_holder_book_cover_list.view.*


class BooksCoverImageListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(s: String) {
        Log.d("imagelist","check img list = $s")
                Glide.with(itemView.context)
                    .load(s)
                    .placeholder(R.drawable.sample_book_cover_two)
                    .override(100, 60)
                    .into(itemView.ivBookCoverPhotoInShelf)

    }
}