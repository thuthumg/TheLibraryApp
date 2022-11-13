package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.viewholders.FilterRatingViewHolder

class FilterRatingAdapter: RecyclerView.Adapter<FilterRatingViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterRatingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_filter_rating,
                parent,false)

        return FilterRatingViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterRatingViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }

}