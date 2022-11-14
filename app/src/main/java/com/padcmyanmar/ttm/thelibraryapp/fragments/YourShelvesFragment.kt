package com.padcmyanmar.ttm.thelibraryapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.BooksAndAudioDetailViewActivity
import com.padcmyanmar.ttm.thelibraryapp.activities.CreateShelfActivity
import com.padcmyanmar.ttm.thelibraryapp.activities.ShelvesListActivity.Companion.newIntent
import com.padcmyanmar.ttm.thelibraryapp.adapters.ShelvesListAdapter
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.SortedBookItemBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.data.models.ShelvesDataModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.ShelvesDataModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.ShelvesItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewpods.FilterAndSortBookListViewPod
import kotlinx.android.synthetic.main.fragment_your_shelves.*


class YourShelvesFragment : Fragment(),BookItemDelegate,ShelvesItemDelegate{

    private val mShelvesDataModel: ShelvesDataModel = ShelvesDataModelImpl

    lateinit var mFilterAndSortBookListViewPod: FilterAndSortBookListViewPod
  //  var bookItemDelegate: BookItemDelegate? = null

    lateinit var mShelvesListAdapter:ShelvesListAdapter
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        bookItemDelegate = context as BookItemDelegate
//    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_shelves, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpShelvesList()
        clickListener()

    }
    private fun setUpShelvesList() {

        mShelvesDataModel.getShelvesList(
            onSuccess = {
               if (it.isNotEmpty())
               {
                   llEmptyView.visibility  = View.GONE
                   rvShelvesList.visibility = View.VISIBLE
                   mShelvesListAdapter = ShelvesListAdapter(this,this)
                   rvShelvesList.adapter = mShelvesListAdapter
                   rvShelvesList.layoutManager = LinearLayoutManager(
                       context,
                       LinearLayoutManager.VERTICAL, false)
                   mShelvesListAdapter.setData(it)
                   rvShelvesList.isNestedScrollingEnabled = false
               }else{
                   rvShelvesList.visibility = View.GONE
                   llEmptyView.visibility  = View.VISIBLE
               }

            },
            onFailure = {
                showToast(it)
            })


    }

    private fun clickListener() {
        mBtnCreateNew.setOnClickListener {
           startActivity(Intent(context,CreateShelfActivity::class.java))
       }
    }


    private fun showToast(it: String) {

        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()


    }

    override fun onResume() {
        super.onResume()
        setUpShelvesList()
    }


    override fun goToBookShelvesListPage(textShelfName: String) {
        context?.let {
            startActivity(newIntent(it,textShelfName))

        }

    }

    override fun callContextualMenuBottomSheetDialogFun() {
        val customButtonSheet = SortedBookItemBottomSheetDialogFragment()
        customButtonSheet.show(requireActivity().supportFragmentManager,"modalSheetDialog")

    }

    override fun callMoreFunc() {

    }


    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {
        startActivity(Intent(context, BooksAndAudioDetailViewActivity::class.java))
    }
}