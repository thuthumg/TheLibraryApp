package com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.delegates.ShelvesItemDelegate
import kotlinx.android.synthetic.main.bottom_sheet_dialog_shelves.*

class ShelvesListMenuBottomSheetDialogFragment(var shelvesItemDelegate: ShelvesItemDelegate) : BottomSheetDialogFragment() {
    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl
    var shelvesName:String = ""
    var shelvesId:Int = 0
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
            dismiss()
            shelvesItemDelegate.renameShelf()
           // startActivity(Intent(context,CreateShelfActivity::class.java))
        }

        llDeleteShelf.setOnClickListener {
            dismiss()
            shelvesItemDelegate.deleteShelf(shelvesId)

        }
    }

    private fun showToast(it: String) {

        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()


    }


    private fun getParamData() {
        if(arguments != null)
        {
            shelvesName = arguments?.getString("shelvesNameId").toString()
            shelvesId = arguments?.getInt("shelvesId")!!

        }
    }

}