package com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.bottom_sheet_dialog_sorted_book_item.*


class SortedBookItemBottomSheetDialogFragment(
    var bookItemDelegate:BookItemDelegate) : BottomSheetDialogFragment() {

    var shelvesName:String = ""
    lateinit var mBooksListVO: BooksListVO
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

        var view =  inflater.inflate(R.layout.bottom_sheet_dialog_sorted_book_item, container, false)

        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getParamData()
      //  tvBottomSheetShelfName.text = shelvesName
        llAddToShelves.setOnClickListener {
            dismiss()
            bookItemDelegate.addToShelvesList(mBooksListVO)
           // startActivity(Intent(context,AddToShelvesActivity::class.java))
        }
    }


    private fun getParamData() {
        if(arguments != null)
        {
            shelvesName = arguments?.getString("shelvesNameId").toString()
            mBooksListVO = arguments?.getSerializable("booksItemParamData") as BooksListVO

            tvBookTitle.text = mBooksListVO.title
            tvAuthor.text = mBooksListVO.author


            mBooksListVO.bookImageHeight?.let { h->
                mBooksListVO.bookImageWidth?.let { w ->
                    context?.let {
                        Glide.with(it)
                            .load(mBooksListVO.bookImage)
                            .override(w, h)
                            .into(ivBookCover)
                    }
                }
            }



        }


    }

}