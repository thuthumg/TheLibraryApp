package com.padcmyanmar.ttm.thelibraryapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemContextualMenuDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewpods.EBooksListViewPod
import kotlinx.android.synthetic.main.fragment_e_books.*

class AudioBooksFragment : Fragment() {

      lateinit var mEBooksListViewPod: EBooksListViewPod
    var bookItemContextualMenuDelegate:BookItemContextualMenuDelegate?= null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        bookItemContextualMenuDelegate = context as BookItemContextualMenuDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPods()
    }

    private fun setUpViewPods() {
        mEBooksListViewPod = vpEBookList as EBooksListViewPod
        bookItemContextualMenuDelegate?.let {
            mEBooksListViewPod.setData(it,true)

        }
    }

}