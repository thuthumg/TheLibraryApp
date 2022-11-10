package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemContextualMenuDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.UnReadBooksViewHolder

class UnReadBooksAdapter(var bookItemContextualMenuDelegate: BookItemContextualMenuDelegate,
                         var checkAudioOrEbooksFlagParam: Boolean) : RecyclerView.Adapter<UnReadBooksViewHolder>() {

    private var checkAudioOrEbooksFlag:Boolean = false
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnReadBooksViewHolder {
      val view =
          LayoutInflater.from(parent.context).inflate(R.layout.view_holder_unread_books_list,
              parent,false)

        return UnReadBooksViewHolder(bookItemContextualMenuDelegate,view)
    }

    override fun onBindViewHolder(holder: UnReadBooksViewHolder, position: Int) {
        checkAudioOrEbooksFlag = checkAudioOrEbooksFlagParam
        holder.bindData(checkAudioOrEbooksFlag)
    }
    fun checkAudioOrEbooks(checkAudioOrEbooksFlagParam:Boolean){
        checkAudioOrEbooksFlag = checkAudioOrEbooksFlagParam
    }
    override fun getItemCount(): Int {
        return 10
    }
}