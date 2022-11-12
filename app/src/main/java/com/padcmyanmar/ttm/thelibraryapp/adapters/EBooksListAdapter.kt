package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.EBooksListViewHolder

class EBooksListAdapter(var bookItemDelegate: BookItemDelegate): RecyclerView.Adapter<EBooksListViewHolder>() {

    private var checkAudioOrEbooksFlag:Boolean = false
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EBooksListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_ebooks_list,
                parent,false)

        return EBooksListViewHolder(bookItemDelegate,view)
    }

    override fun onBindViewHolder(holder: EBooksListViewHolder, position: Int) {
        holder.bindData(checkAudioOrEbooksFlag)
    }
    fun checkAudioOrEbooks(checkAudioOrEbooksFlagParam:Boolean){
        checkAudioOrEbooksFlag = checkAudioOrEbooksFlagParam
    }
    override fun getItemCount(): Int {
        return 10
    }
}