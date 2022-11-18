package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.view.View
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.AddToShelvesListDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewpods.OverlapRecyclerviewViewPod
import kotlinx.android.synthetic.main.view_holder_add_to_shelves_list.view.*
import kotlinx.android.synthetic.main.view_holder_add_to_shelves_list.view.tvShelfName


class AddToShelvesListViewHolder(itemView: View, var mBooksListVO: BooksListVO,
                                 addToShelvesDelegate:AddToShelvesListDelegate) : RecyclerView.ViewHolder(itemView){
    lateinit var mShelfVO: ShelfVO
    private lateinit var mOverlapRecyclerviewViewPod:OverlapRecyclerviewViewPod
  //  var mBooksListVOList : ArrayList<BooksListVO> = arrayListOf()

  //  lateinit var mShelfVOList: List<ShelfVO>


    init {
        setUpViewPod()
        itemView.chkBookItem.setOnCheckedChangeListener{ compoundButton: CompoundButton, b: Boolean ->

           /* if(b)
            {
//                mShelfVO.booksList?.let {
//                    it.forEach { bookListVO->
//                        mBooksListVOList.add(bookListVO)
//                    }
//                }
//
//                mBooksListVOList.add(mBooksListVO)
                mShelfVO.isSelected = true

            }else{
                mShelfVO.isSelected = false
//                mBooksListVOList.remove(mBooksListVO)
            }
          //  mShelfVO.booksList = mBooksListVOList*/


           // mShelfVOList.add(mShelfVO)
            mShelfVO.isSelected = b
            addToShelvesDelegate.addToShelvesData(mShelfVO.id,mShelfVO.isSelected)
        }

    }

    private fun setUpViewPod() {
        mOverlapRecyclerviewViewPod = itemView.vpBookCoverOverlapList as OverlapRecyclerviewViewPod
    }


    fun bindData(shelfVO: ShelfVO) {
        mShelfVO = shelfVO
        //for cover image
       var imgListArray: ArrayList<String> = arrayListOf()

        shelfVO.booksList?.forEach {
            imgListArray.add(it.bookImage.toString())
        }
        mOverlapRecyclerviewViewPod.setData(imgListArray)

        // for shelf name
        itemView.tvShelfName.text = shelfVO.shelfName

        //for shelf book count
        when (shelfVO.booksList?.size) {
            0 -> { itemView.tvShelfBookCount.text = "0 Book"
                imgListArray.add("sample")
                imgListArray.add("sample")
            }
            1 -> { itemView.tvShelfBookCount.text = "${shelfVO.booksList?.size} Book"}
            else -> { itemView.tvShelfBookCount.text = "${shelfVO.booksList?.size} Books"}
        }

    }
}