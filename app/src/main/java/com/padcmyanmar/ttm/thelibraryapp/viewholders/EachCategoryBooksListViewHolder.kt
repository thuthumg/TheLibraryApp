package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_holder_each_category_book_list.view.*


class EachCategoryBooksListViewHolder(itemView: View, bookItemDelegate: BookItemDelegate) : RecyclerView.ViewHolder(itemView){

    lateinit var mBooksListVO:BooksListVO

    init {

        itemView.ivContextualMenu.setOnClickListener {
            bookItemDelegate.callContextualMenuBottomSheetDialogFun(mBooksListVO)

        }
        itemView.ivBookCoverPhoto.setOnClickListener {
            bookItemDelegate.callBookDetailPage(mBooksListVO)
        }

    }

    fun bindData(booksListVO: BooksListVO) {

        mBooksListVO = booksListVO

//
//        booksListVO.bookImageHeight?.let { h->
//            booksListVO.bookImageWidth?.let { w ->
//                Glide.with(itemView.context)
//                    .load(booksListVO.bookImage)
//                    .override(w, h)
//                    .into( itemView.ivBookCoverPhoto)
//            }
//        }


        Glide.with(itemView.context)
            .load(booksListVO.bookImage)
            .placeholder(R.drawable.empty_book_icon)
            .into( itemView.ivBookCoverPhoto)




        itemView.tvBookTitle.text = booksListVO.title
        itemView.tvAuthor.text = booksListVO.author

    }



}