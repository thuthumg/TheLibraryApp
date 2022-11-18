package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.ttm.thelibraryapp.adapters.UnReadBooksAdapter
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_holder_ebooks_and_audio_book_list.view.*


class EBooksAndAudioBooksListViewHolder(var bookItemDelegate: BookItemDelegate, itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var mUnReadBooksAdapter: UnReadBooksAdapter
    private var checkAudioOrEbooksFlag:Boolean = false
    private var mCategoryBooksListVO:CategoryBooksListVO? = null

    init {
        itemView.llMore.setOnClickListener{
            bookItemDelegate.callMoreFunc()
        }

    }

    fun bindData(
        categoryBooksListVO: CategoryBooksListVO,
        checkAudioOrEbookFlagParam: Boolean
    ){
        mCategoryBooksListVO = categoryBooksListVO

        checkAudioOrEbooksFlag = checkAudioOrEbookFlagParam
        mCategoryBooksListVO?.books?.let {
            setUpEBookItemRecyclerView(it,checkAudioOrEbooksFlag)
        }

        itemView.tvBookCategoryName.text = categoryBooksListVO.displayName
    }

    private fun setUpEBookItemRecyclerView(
        mBooksListVO: List<BooksListVO>,
        checkAudioOrEbooksFlag: Boolean
    ) {
        mCategoryBooksListVO?.let {
            mUnReadBooksAdapter = UnReadBooksAdapter(it,bookItemDelegate,checkAudioOrEbooksFlag)
            itemView.rvUnReadBooksItemList.adapter = mUnReadBooksAdapter
            itemView.rvUnReadBooksItemList.layoutManager = LinearLayoutManager(
                itemView.context,
                LinearLayoutManager.HORIZONTAL, false
            )
            mUnReadBooksAdapter.setNewData(mBooksListVO)
        }

    }
}