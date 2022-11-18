package com.padcmyanmar.ttm.thelibraryapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.BooksAndAudioDetailViewActivity
import com.padcmyanmar.ttm.thelibraryapp.activities.CreateShelfActivity
import com.padcmyanmar.ttm.thelibraryapp.activities.ShelvesListDetailActivity.Companion.newIntent
import com.padcmyanmar.ttm.thelibraryapp.adapters.ShelvesListAdapter
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.SortedBookItemBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.YoursShelvesPresenter
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.YoursShelvesPresenterImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.YourShelvesView
import kotlinx.android.synthetic.main.fragment_your_shelves.*


class YourShelvesFragment : Fragment(),YourShelvesView{//,BookItemDelegate,ShelvesItemDelegate


    //Presenter
    private lateinit var mPresent:YoursShelvesPresenter

    lateinit var mShelvesListAdapter:ShelvesListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_shelves, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpShelvesList()
        clickListener()
        mPresent.onUiReady(this)
    }

    private fun setUpPresenter() {
      mPresent = ViewModelProvider(this)[YoursShelvesPresenterImpl::class.java]
      mPresent.initView(this)
    }

    private fun setUpShelvesList() {
        mShelvesListAdapter = ShelvesListAdapter(mPresent,mPresent)
        rvShelvesList.adapter = mShelvesListAdapter
        rvShelvesList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false)
        rvShelvesList.isNestedScrollingEnabled = false

    }

    private fun clickListener() {
        mBtnCreateNew.setOnClickListener {
           startActivity(Intent(context,CreateShelfActivity::class.java))
       }
    }

 /*   override fun goToBookShelvesListPage(textShelfName: String) {
        context?.let {
            startActivity(newIntent(it,textShelfName))

        }

    }*/

    override fun showShelvesListData(shelvesList: List<ShelfVO>) {
        if (shelvesList.isNotEmpty())
        {
            llEmptyView.visibility  = View.GONE
            rvShelvesList.visibility = View.VISIBLE
            mShelvesListAdapter.setData(shelvesList)

        }else{
            rvShelvesList.visibility = View.GONE
            llEmptyView.visibility  = View.VISIBLE
        }
    }

    override fun showReadBooksList(readBooksListVO: List<BooksListVO>) {

    }

    override fun navigateToContextualMenuBottomSheetDialog(mBooksListVO: BooksListVO?) {
        val customButtonSheet = SortedBookItemBottomSheetDialogFragment(mPresent)
        var args: Bundle = Bundle()
        args.putSerializable("booksItemParamData", mBooksListVO)
        customButtonSheet.arguments = args
        customButtonSheet.show(requireActivity().supportFragmentManager,"modalSheetDialog")
    }

    override fun viewAsFuncListener(checkedViewAsRadioButtonText: String) {

    }

    override fun sortFuncListener(checkedViewAsRadioButtonText: String) {

    }

    override fun navigateToViewAsBottomSheetDialog(changeListViewType: String) {

    }

    override fun navigateToSortByBottomSheetDialog(changeSortType: String) {

    }

    override fun navigateToBookDetailPage(mBooksListVO: BooksListVO?) {
        startActivity(Intent(context, BooksAndAudioDetailViewActivity::class.java))
    }

    override fun callFilterByCategory(chipNameList: ArrayList<String>) {

    }

    override fun navigateToShelfListPage(textShelfName: String, mShelfVO: ShelfVO?) {
        startActivity(newIntent(context,textShelfName,mShelfVO))
    }


    override fun showError(errorString: String) {
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show()
    }
}