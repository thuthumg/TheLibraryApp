package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.UnReadBooksViewHolder

class UnReadBooksAdapter(var bookItemDelegate: BookItemDelegate,
                         var checkAudioOrEbooksFlagParam: Boolean) : RecyclerView.Adapter<UnReadBooksViewHolder>() {



    var mBooksListVO: List<BooksListVO> = listOf()
    private var checkAudioOrEbooksFlag:Boolean = false
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnReadBooksViewHolder {
      val view =
          LayoutInflater.from(parent.context).inflate(R.layout.view_holder_unread_books_list,
              parent,false)

        return UnReadBooksViewHolder(bookItemDelegate,view)
    }

    override fun onBindViewHolder(holder: UnReadBooksViewHolder, position: Int) {
        checkAudioOrEbooksFlag = checkAudioOrEbooksFlagParam

        if(mBooksListVO.isNotEmpty())
        holder.bindData(mBooksListVO[position],checkAudioOrEbooksFlag)
    }
    override fun getItemCount(): Int {
        return mBooksListVO.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(booksListVO: List<BooksListVO>){
        mBooksListVO = booksListVO
        notifyDataSetChanged()
    }
}