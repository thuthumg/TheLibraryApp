package com.padcmyanmar.ttm.thelibraryapp.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.viewholders.BooksCoverImageListViewHolder
import kotlin.random.Random


class BooksCoverImageListAdapter: RecyclerView.Adapter<BooksCoverImageListViewHolder>() {

    private var mImgList:ArrayList<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksCoverImageListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_book_cover_list,
                parent,false)

        return BooksCoverImageListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksCoverImageListViewHolder, position: Int) {

        if(mImgList.isNotEmpty()){
            holder.bindData(mImgList[position])
        }

//        holder.itemView.elevation = 30.0f * position
//        val rand = Random
//        holder.itemView.setBackgroundColor(
//            Color.rgb(
//                rand.nextInt(255),
//                rand.nextInt(255),
//                rand.nextInt(255)
//            )
//        )


    }

    override fun getItemCount(): Int {
       return mImgList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(imgList:ArrayList<String>){
        this.mImgList = imgList
        notifyDataSetChanged()
    }


}