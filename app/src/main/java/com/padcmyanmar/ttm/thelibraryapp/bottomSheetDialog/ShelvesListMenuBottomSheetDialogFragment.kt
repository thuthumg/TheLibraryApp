package com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.CreateShelfActivity
import com.padcmyanmar.ttm.thelibraryapp.activities.ShelvesListActivity
import com.padcmyanmar.ttm.thelibraryapp.adapters.ShelvesListAdapter
import com.padcmyanmar.ttm.thelibraryapp.data.models.ShelvesDataModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.ShelvesDataModelImpl
import kotlinx.android.synthetic.main.bottom_sheet_dialog_shelves.*
import kotlinx.android.synthetic.main.fragment_your_shelves.*

class ShelvesListMenuBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private val mShelvesDataModel: ShelvesDataModel = ShelvesDataModelImpl
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

        var view =  inflater.inflate(R.layout.bottom_sheet_dialog_shelves, container, false)

        return view.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getParamData()
        tvBottomSheetShelfName.text = shelvesName
        clickListener()
    }

    private fun clickListener() {
        llRenameShelf.setOnClickListener {


           // startActivity(Intent(context,CreateShelfActivity::class.java))
        }

        llDeleteShelf.setOnClickListener {
            mShelvesDataModel.deleteShelf(
                1,
                onSuccess = {
                    showToast(it)
                    dismiss()

                },
                onFailure = {
                    showToast(it)
                })
        }
    }

    private fun showToast(it: String) {

        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()


    }


    private fun getParamData() {
        if(arguments != null)
        {
            shelvesName = arguments?.getString("shelvesNameId").toString()
        }
    }

}