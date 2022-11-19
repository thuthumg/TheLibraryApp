package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.GoogleBookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.GooglePlayBookViewHolder

class GooglePlayBookAdapter  (var googleBookItemDelegate: GoogleBookItemDelegate) : RecyclerView.Adapter<GooglePlayBookViewHolder>() {

    var mBooksVOList: List<BooksListVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GooglePlayBookViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_google_play_book,
                parent, false
            )

        return GooglePlayBookViewHolder(googleBookItemDelegate,view)
    }

    override fun onBindViewHolder(holder: GooglePlayBookViewHolder, position: Int) {
        if(mBooksVOList.isNotEmpty()) {
            holder.bindData(mBooksVOList[position])
        }
    }

    override fun getItemCount(): Int {
        return mBooksVOList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(booksVOList: List<BooksListVO>){
        mBooksVOList = booksVOList
        notifyDataSetChanged()
    }
}
