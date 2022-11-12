package com.padcmyanmar.ttm.thelibraryapp.fragments.bottomSheetDialog

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.get
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import kotlinx.android.synthetic.main.bottom_sheet_dialog_view_as.*
import kotlinx.android.synthetic.main.bottom_sheet_dialog_view_as.view.*


class ViewAsBottomSheetDialogFragment(var sortAndViewAsDelegate: SortAndViewAsDelegate) : BottomSheetDialogFragment() {

    lateinit var bottomSheetView: View
    var listViewTypeFlag:String = ""

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

        bottomSheetView =  inflater.inflate(R.layout.bottom_sheet_dialog_view_as, container, false)
        getListViewTypeDataFromArgument()
        return bottomSheetView.rootView
    }

    private fun getListViewTypeDataFromArgument() {
        if(arguments != null)
        {
            listViewTypeFlag = arguments?.getString("viewType").toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewAsRadioData()
        clickListener()
    }

    private fun setUpViewAsRadioData() {
        Log.d("viewas","check selected = $listViewTypeFlag")
        when(listViewTypeFlag)
        {
            getString(R.string.lbl_view_list)->{
                bottomSheetView.rbViewGroup.rbList.isChecked = true
                bottomSheetView.rbViewGroup.rbLargeGrid.isChecked = false
                bottomSheetView.rbViewGroup.rbSmallGrid.isChecked = false

            }
            getString(R.string.lbl_large_grid_list)->{
                bottomSheetView.rbViewGroup.rbList.isChecked = false
                bottomSheetView.rbViewGroup.rbLargeGrid.isChecked = true
                bottomSheetView.rbViewGroup.rbSmallGrid.isChecked = false
            }
            getString(R.string.lbl_small_grid_list)->{
                bottomSheetView.rbViewGroup.rbList.isChecked = false
                bottomSheetView.rbViewGroup.rbLargeGrid.isChecked = false
                bottomSheetView.rbViewGroup.rbSmallGrid.isChecked = true
            }
        }

    }

    private fun clickListener() {

        bottomSheetView.rbViewGroup.setOnCheckedChangeListener { radioGroup, i ->
            if (bottomSheetView.rbViewGroup.checkedRadioButtonId === R.id.rbLargeGrid)
            {

                dismiss()
                sortAndViewAsDelegate?.callbackViewAsFunc(
                    bottomSheetView.rbViewGroup.rbLargeGrid.text.toString()
                )
            }
            else  if (bottomSheetView.rbViewGroup.checkedRadioButtonId === R.id.rbSmallGrid)
            {
                dismiss()
                sortAndViewAsDelegate?.callbackViewAsFunc(
                    bottomSheetView.rbViewGroup.rbSmallGrid.text.toString()
                )

            }else{
                dismiss()
                sortAndViewAsDelegate?.callbackViewAsFunc(
                    bottomSheetView.rbViewGroup.rbList.text.toString()
                )
            }
        }

    }
}