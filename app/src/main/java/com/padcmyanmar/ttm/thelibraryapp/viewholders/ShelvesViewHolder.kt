package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.ShelvesItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewpods.OverlapRecyclerviewViewPod
import kotlinx.android.synthetic.main.view_holder_shelves.view.*

class ShelvesViewHolder(
    itemView: View,
    bookItemDelegate: BookItemDelegate,
    shelvesItemDelegate: ShelvesItemDelegate
) : RecyclerView.ViewHolder(itemView){

    private var mShelfVO: ShelfVO? = null
    lateinit var mOverlapRecyclerviewViewPod: OverlapRecyclerviewViewPod

    init {
        itemView.ivGoToShelvesList.setOnClickListener {
            shelvesItemDelegate.goToShelvesDetailPage(itemView.tvShelfName.text.toString(),mShelfVO)
        }

        setUpBookCoverList()
    }

    fun bindData(shelfVOParam: ShelfVO) {
        mShelfVO = shelfVOParam
        itemView.tvShelfName.text = shelfVOParam.shelfName

        var imgListArray:ArrayList<String>  = arrayListOf()

        when (shelfVOParam.booksList?.size) {
            0 -> {itemView.tvBookCount.text = "0 Book"
            imgListArray.add("sample")
                imgListArray.add("sample")
            }
            1 -> {itemView.tvBookCount.text = "${shelfVOParam.booksList?.size} Book"}
            else -> {itemView.tvBookCount.text = "${shelfVOParam.booksList?.size} Books"}
        }

        shelfVOParam.booksList?.forEach {

            imgListArray.add(it.bookImage.toString())
        }
        mOverlapRecyclerviewViewPod.setData(imgListArray)

    }

    private fun setUpBookCoverList(){
        mOverlapRecyclerviewViewPod = itemView.vpBooksCoverImgList as OverlapRecyclerviewViewPod
    }



}