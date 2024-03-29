package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.EachCategoryBooksListViewHolder


class EachCategoryBooksListAdapter(var bookItemDelegate: BookItemDelegate) : RecyclerView.Adapter<EachCategoryBooksListViewHolder>(){


    var mBooks: List<BooksListVO> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EachCategoryBooksListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_each_category_book_list,
                parent,false)

        return EachCategoryBooksListViewHolder(view,bookItemDelegate)
    }

    override fun onBindViewHolder(holder: EachCategoryBooksListViewHolder, position: Int) {


       if(mBooks.isNotEmpty())
       {
           holder.bindData(mBooks[position])
       }

    }

    override fun getItemCount(): Int {
        return mBooks.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(books: List<BooksListVO>){
        mBooks = books
        notifyDataSetChanged()
    }

}