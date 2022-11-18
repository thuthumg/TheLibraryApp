package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.ViewTypeListBookListViewHolder


class ViewTypeListBookListAdapter (
    var bookItemDelegate: BookItemDelegate,
    var checkAudioOrEbooksFlagParam: Boolean) : RecyclerView.Adapter<ViewTypeListBookListViewHolder>(){

    private var checkAudioOrEbooksFlag:Boolean = false

    private var mBooksListVoList: List<BooksListVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTypeListBookListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_list_view_type_book,
                parent,false)

        return ViewTypeListBookListViewHolder(bookItemDelegate = bookItemDelegate,
            view)
    }

    override fun onBindViewHolder(holder: ViewTypeListBookListViewHolder, position: Int) {
        checkAudioOrEbooksFlag = checkAudioOrEbooksFlagParam
        if(mBooksListVoList.isNotEmpty())
        {
            holder.bindData(checkAudioOrEbooksFlag,mBooksListVoList[position])

        }
    }

    override fun getItemCount(): Int {
        return mBooksListVoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(booksListVoList: List<BooksListVO>){
        mBooksListVoList = booksListVoList
        notifyDataSetChanged()
    }
}