package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.viewholders.GooglePlayBookViewHolder

class GooglePlayBookAdapter  () : RecyclerView.Adapter<GooglePlayBookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GooglePlayBookViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_google_play_book,
                parent, false
            )

        return GooglePlayBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: GooglePlayBookViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }
}
