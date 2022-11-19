package com.padcmyanmar.ttm.thelibraryapp.viewholders

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.view_holder_unread_books_list.view.*

class UnReadBooksViewHolder(
    categoryBooksListVO: CategoryBooksListVO,
    var bookItemDelegate: BookItemDelegate,
    itemView: View
) : RecyclerView.ViewHolder(itemView){

    private var categoryListName:String = ""
    var categoryListId:Int? = 0

    lateinit var mBooksListVO:BooksListVO

    init {

        categoryListName = categoryBooksListVO.listName.toString()
        categoryListId = categoryBooksListVO.listId


//        mBooksListVO?.categoryId = categoryListId
//        mBooksListVO?.categoryName = categoryListName

        itemView.ivContextualMenu.setOnClickListener {
              bookItemDelegate.callContextualMenuBottomSheetDialogFun(mBooksListVO)
        }

        itemView.cvBookCover.setOnClickListener {

//            mBooksListVO?.categoryName = categoryListName
//            mBooksListVO?.categoryId = categoryListId

            Log.d("check","check data ${mBooksListVO?.categoryName}")
            bookItemDelegate.callBookDetailPage(mBooksListVO)
        }
    }


    fun bindData(booksListVO: BooksListVO, checkAudioOrEbooksFlagParam: Boolean){

        mBooksListVO = booksListVO

        mBooksListVO.categoryId = categoryListId
        mBooksListVO.categoryName = categoryListName

        if(checkAudioOrEbooksFlagParam)
        {
            itemView.ivAudio.visibility = View.VISIBLE
        }else{
            itemView.ivAudio.visibility = View.GONE
        }

        itemView.tvBookTitle.text = booksListVO.title
        itemView.tvBookAuthor.text = booksListVO.author


        booksListVO.bookImageHeight?.let { h->
            booksListVO.bookImageWidth?.let { w ->
                Glide.with(itemView.context)
                    .load(booksListVO.bookImage)
                    .placeholder(R.drawable.empty_book_icon)
                    .override(w, h)
 //                   .placeholder(R.drawable.sample_book_cover) //5
//            .error(R.drawable.sample_book_pic) //6
//            .fallback(R.drawable.sample_book_cover_two) //7
                    .into(itemView.ivBookCoverPhoto)
            }
        }

    }
}
