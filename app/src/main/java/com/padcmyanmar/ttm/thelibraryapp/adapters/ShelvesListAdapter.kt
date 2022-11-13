package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.ShelvesItemDelegate

import com.padcmyanmar.ttm.thelibraryapp.viewholders.ShelvesViewHolder

class ShelvesListAdapter(var bookItemDelegate: BookItemDelegate,var shelvesItemDelegate: ShelvesItemDelegate) : RecyclerView.Adapter<ShelvesViewHolder>(){


    private var mShelvesVOList: List<ShelfVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelvesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_shelves,
                parent,false)

        return ShelvesViewHolder(view,bookItemDelegate,shelvesItemDelegate)
    }

    override fun onBindViewHolder(holder: ShelvesViewHolder, position: Int) {

        if(mShelvesVOList.isNotEmpty())
        {
            holder.bindData(mShelvesVOList[position])
        }
    }

    override fun getItemCount(): Int {
        return mShelvesVOList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(mShelvesVOList: List<ShelfVO>)
    {
        this.mShelvesVOList = mShelvesVOList
        notifyDataSetChanged()
    }
}