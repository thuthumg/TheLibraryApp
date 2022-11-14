package com.padcmyanmar.ttm.thelibraryapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.BooksAndAudioDetailViewActivity
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.SortByBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.SortedBookItemBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.ViewAsBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

import com.padcmyanmar.ttm.thelibraryapp.viewpods.FilterAndSortBookListViewPod
import kotlinx.android.synthetic.main.fragment_your_books.*


class YourBooksFragment : Fragment(),BookItemDelegate, SortAndViewAsDelegate {
    lateinit var mFilterAndSortBookListViewPod: FilterAndSortBookListViewPod
//    var bookItemDelegate: BookItemDelegate? = null
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

        return inflater.inflate(R.layout.fragment_your_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPods()
    }

    private fun setUpViewPods() {
        mFilterAndSortBookListViewPod = vpFilterAndSortBookList as FilterAndSortBookListViewPod
        mFilterAndSortBookListViewPod.setData(this,this)


    }
    override fun callbackViewAsFunc(checkedViewAsRadioButtonText: String) {
        when (checkedViewAsRadioButtonText) {

            getString(R.string.lbl_large_grid_list) -> {

                mFilterAndSortBookListViewPod.changeListViewType(getString(R.string.lbl_large_grid_list))
            }
            getString(R.string.lbl_small_grid_list) -> {
                mFilterAndSortBookListViewPod.changeListViewType(getString(R.string.lbl_small_grid_list))

            }
            else -> {
                mFilterAndSortBookListViewPod.changeListViewType(getString(R.string.lbl_view_list))
            }
        }
    }

    override fun callbackSortFunc(checkedViewAsRadioButtonText: String) {
        when (checkedViewAsRadioButtonText) {

            getString(R.string.lbl_title_type) -> {
                mFilterAndSortBookListViewPod.changeSortType(getString(R.string.lbl_title_type))
             }
            getString(R.string.lbl_author_type) -> {
                mFilterAndSortBookListViewPod.changeSortType(getString(R.string.lbl_title_type))
            }
            else -> {
                mFilterAndSortBookListViewPod.changeSortType(getString(R.string.lbl_title_type))
            }
        }
    }

    override fun callViewAsBottomSheetDialog(changeListViewType: String) {
        val viewAsBottomSheet = ViewAsBottomSheetDialogFragment(this)
        var args: Bundle = Bundle()
        args.putString("viewType", changeListViewType)
        viewAsBottomSheet.arguments = args
        viewAsBottomSheet.show(requireActivity().supportFragmentManager, "modalSheetDialog")
    }

    override fun callSortByBottomSheetDialog(changeSortType: String) {
        val sortByBottomSheet = SortByBottomSheetDialogFragment(this)
        var args: Bundle = Bundle()
        args.putString("sortByType", changeSortType)
        sortByBottomSheet.arguments = args
        sortByBottomSheet.show(requireActivity().supportFragmentManager, "modalSheetDialog")
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