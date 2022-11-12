package com.padcmyanmar.ttm.thelibraryapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewpods.EBooksListViewPod
import kotlinx.android.synthetic.main.fragment_e_books.*

class EBooksFragment : Fragment() {

    lateinit var mEBooksListViewPod: EBooksListViewPod
    var bookItemDelegate:BookItemDelegate?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_e_books, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bookItemDelegate = context as BookItemDelegate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPods()
    }

    private fun setUpViewPods() {
        mEBooksListViewPod = vpEBookList as EBooksListViewPod
        bookItemDelegate?.let {
            mEBooksListViewPod.setData(it,false)

        }

    }


}