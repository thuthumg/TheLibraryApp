package com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import kotlinx.android.synthetic.main.bottom_sheet_dialog_book_item.*

class BookItemBottomSheetDialogFragment() : BottomSheetDialogFragment() {

    lateinit var booksListVO:BooksListVO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        var view =  inflater.inflate(R.layout.bottom_sheet_dialog_book_item, container, false)

        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getParamData()



    }


    private fun getParamData() {
        if(arguments != null)
        {
            booksListVO = arguments?.getSerializable("booksItemParamData") as BooksListVO
            tvBookTitle.text = booksListVO.title
            tvAuthor.text = booksListVO.author

            booksListVO.bookImageHeight?.let { h->
                booksListVO.bookImageWidth?.let { w ->
                    context?.let {
                        Glide.with(it)
                            .load(booksListVO.bookImage)
                            .override(w, h)
                            .into(ivBookCover)
                    }
                }
            }
        }
    }
}