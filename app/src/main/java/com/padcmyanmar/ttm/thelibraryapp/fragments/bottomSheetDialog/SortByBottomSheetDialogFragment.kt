package com.padcmyanmar.ttm.thelibraryapp.fragments.bottomSheetDialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import kotlinx.android.synthetic.main.bottom_sheet_dialog_sort_by.view.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog_view_as.view.*

class SortByBottomSheetDialogFragment (var sortAndViewAsDelegate: SortAndViewAsDelegate) : BottomSheetDialogFragment() {

    lateinit var bottomSheetView: View
    var sortTypeFlag:String = ""

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

        bottomSheetView =  inflater.inflate(R.layout.bottom_sheet_dialog_sort_by, container, false)
        getListViewTypeDataFromArgument()
        return bottomSheetView.rootView
    }

    private fun getListViewTypeDataFromArgument() {
        if(arguments != null)
        {
            sortTypeFlag = arguments?.getString("sortByType").toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewAsRadioData()
        clickListener()
    }

    private fun setUpViewAsRadioData() {
        Log.d("viewas","check selected = $sortTypeFlag")
        when(sortTypeFlag)
        {
            getString(R.string.lbl_recently_opened_type)->{
                bottomSheetView.rbSortByGroup.rbRecentlyOpened.isChecked = true
                bottomSheetView.rbSortByGroup.rbTitle.isChecked = false
                bottomSheetView.rbSortByGroup.rbAuthor.isChecked = false

            }
            getString(R.string.lbl_title_type)->{
                bottomSheetView.rbSortByGroup.rbRecentlyOpened.isChecked = false
                bottomSheetView.rbSortByGroup.rbTitle.isChecked = true
                bottomSheetView.rbSortByGroup.rbAuthor.isChecked = false
            }
            getString(R.string.lbl_author_type)->{
                bottomSheetView.rbSortByGroup.rbRecentlyOpened.isChecked = false
                bottomSheetView.rbSortByGroup.rbTitle.isChecked = false
                bottomSheetView.rbSortByGroup.rbAuthor.isChecked = true
            }
        }

    }

    private fun clickListener() {

        bottomSheetView.rbSortByGroup.setOnCheckedChangeListener { radioGroup, i ->
            if (bottomSheetView.rbSortByGroup.checkedRadioButtonId === R.id.rbAuthor)
            {

                dismiss()
                sortAndViewAsDelegate?.callbackSortFunc(
                    bottomSheetView.rbSortByGroup.rbAuthor.text.toString()
                )
            }
            else  if (bottomSheetView.rbSortByGroup.checkedRadioButtonId === R.id.rbTitle)
            {
                dismiss()
                sortAndViewAsDelegate?.callbackSortFunc(
                    bottomSheetView.rbSortByGroup.rbTitle.text.toString()
                )

            }else{
                dismiss()
                sortAndViewAsDelegate?.callbackSortFunc(
                    bottomSheetView.rbSortByGroup.rbRecentlyOpened.text.toString()
                )
            }
        }

    }
}