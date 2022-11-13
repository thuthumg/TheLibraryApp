package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.ShelvesItemDelegate
import kotlinx.android.synthetic.main.view_holder_shelves.view.*

class ShelvesViewHolder(
    itemView: View,
    bookItemDelegate: BookItemDelegate,
    shelvesItemDelegate: ShelvesItemDelegate
) : RecyclerView.ViewHolder(itemView){

    private var mShelfVO: ShelfVO? = null

    init {
        itemView.ivGoToShelvesList.setOnClickListener {
            shelvesItemDelegate.goToBookShelvesListPage(itemView.tvShelfName.text.toString())
        }


    }

    fun bindData(shelfVOParam: ShelfVO) {
        this.mShelfVO = shelfVOParam
        itemView.tvShelfName.text = shelfVOParam.shelfName
        itemView.tvBookCount.text = "0 Book"
    }



}