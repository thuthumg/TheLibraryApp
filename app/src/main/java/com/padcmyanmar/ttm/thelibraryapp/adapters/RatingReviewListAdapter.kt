package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.viewholders.RatingReviewListViewHolder


class RatingReviewListAdapter () : RecyclerView.Adapter<RatingReviewListViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingReviewListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_rating_review_list,
                parent,false)

        return RatingReviewListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatingReviewListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }

}