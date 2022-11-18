package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.LargeBooksListViewHolder

class LargeBooksListAdapter(
var bookItemDelegate: BookItemDelegate,
var checkAudioOrEbooksFlagParam: Boolean) : RecyclerView.Adapter<LargeBooksListViewHolder>(){

    private var checkAudioOrEbooksFlag:Boolean = false

    private var mBooksListVoList: List<BooksListVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LargeBooksListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_book,
                parent,false)

        return LargeBooksListViewHolder(bookItemDelegate = bookItemDelegate,
            view)
    }

    override fun onBindViewHolder(holder: LargeBooksListViewHolder, position: Int) {
        checkAudioOrEbooksFlag = checkAudioOrEbooksFlagParam

        if(mBooksListVoList.isNotEmpty())
        {
            holder.bindData(checkAudioOrEbooksFlag,mBooksListVoList[position])

        }
    }

    override fun getItemCount(): Int {
        return mBooksListVoList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(booksListVoList: List<BooksListVO>){
        mBooksListVoList = booksListVoList
        notifyDataSetChanged()
    }
}