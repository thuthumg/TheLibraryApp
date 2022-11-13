package com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.AddToShelvesActivity
import kotlinx.android.synthetic.main.bottom_sheet_dialog_sorted_book_item.*

class SortedBookItemBottomSheetDialogFragment : BottomSheetDialogFragment() {

    var shelvesName:String = ""
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
      //  getParamData()
      //  tvBottomSheetShelfName.text = shelvesName
        llAddToShelves.setOnClickListener {
            dismiss()
            startActivity(Intent(context,AddToShelvesActivity::class.java))
        }
    }


    private fun getParamData() {
        if(arguments != null)
        {
            shelvesName = arguments?.getString("shelvesNameId").toString()
        }
    }

}