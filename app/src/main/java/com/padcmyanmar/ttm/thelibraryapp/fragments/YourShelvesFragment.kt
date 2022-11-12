package com.padcmyanmar.ttm.thelibraryapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.CreateShelfActivity
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import com.padcmyanmar.ttm.thelibraryapp.fragments.bottomSheetDialog.SortByBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.fragments.bottomSheetDialog.ViewAsBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.viewpods.FilterAndSortBookListViewPod
import kotlinx.android.synthetic.main.fragment_your_books.*
import kotlinx.android.synthetic.main.fragment_your_shelves.*



class YourShelvesFragment : Fragment() {


    lateinit var mFilterAndSortBookListViewPod: FilterAndSortBookListViewPod
    var bookItemDelegate: BookItemDelegate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bookItemDelegate = context as BookItemDelegate
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_shelves, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListener()

    }
    private fun clickListener() {
       cvCreateNew.setOnClickListener {
           startActivity(Intent(context,CreateShelfActivity::class.java))
       }
    }





}