package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.EBooksAndAudioBooksListViewHolder

class EBooksAndAudioBooksListAdapter(var bookItemDelegate: BookItemDelegate): RecyclerView.Adapter<EBooksAndAudioBooksListViewHolder>() {

    private var checkAudioOrEbooksFlag:Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EBooksAndAudioBooksListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_ebooks_and_audio_book_list,
                parent,false)

        return EBooksAndAudioBooksListViewHolder(bookItemDelegate,view)
    }

    override fun onBindViewHolder(holder: EBooksAndAudioBooksListViewHolder, position: Int) {
        holder.bindData(checkAudioOrEbooksFlag)
    }
    fun checkAudioOrEbooks(checkAudioOrEbooksFlagParam:Boolean){
        checkAudioOrEbooksFlag = checkAudioOrEbooksFlagParam
    }
    override fun getItemCount(): Int {
        return 10
    }
}