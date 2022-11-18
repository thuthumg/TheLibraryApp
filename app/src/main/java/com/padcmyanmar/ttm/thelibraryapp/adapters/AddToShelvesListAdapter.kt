package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.AddToShelvesListDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewholders.AddToShelvesListViewHolder

class AddToShelvesListAdapter(var mBooksListVO: BooksListVO,var addToShelvesDelegate: AddToShelvesListDelegate) : RecyclerView.Adapter<AddToShelvesListViewHolder>(){

   private var mShelvesList: List<ShelfVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToShelvesListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_add_to_shelves_list,
                parent,false)

        return AddToShelvesListViewHolder(view,mBooksListVO,addToShelvesDelegate)
    }
    override fun getItemCount(): Int {
        return mShelvesList.size
    }

    override fun onBindViewHolder(holder: AddToShelvesListViewHolder, position: Int) {

       if(mShelvesList.isNotEmpty())
       {
           holder.bindData(mShelvesList[position])

       }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(shelvesList: List<ShelfVO>){
        mShelvesList = shelvesList
        notifyDataSetChanged()
    }

}