package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.AddToShelvesListViewHolder
import com.padcmyanmar.ttm.thelibraryapp.viewholders.BookListViewHolder

class AddToShelvesListAdapter (
    var bookItemDelegate: BookItemDelegate,
    var checkAudioOrEbooksFlagParam: Boolean) : RecyclerView.Adapter<AddToShelvesListViewHolder>(){

    private var checkAudioOrEbooksFlag:Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToShelvesListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_add_to_shelves_list,
                parent,false)

        return AddToShelvesListViewHolder(bookItemDelegate = bookItemDelegate,
            view)
    }
    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: AddToShelvesListViewHolder, position: Int) {
       // checkAudioOrEbooksFlag = checkAudioOrEbooksFlagParam
       // holder.bindData(checkAudioOrEbooksFlag)
    }
}