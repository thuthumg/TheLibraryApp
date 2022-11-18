package com.padcmyanmar.ttm.thelibraryapp.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.AddToShelvesActivity
import com.padcmyanmar.ttm.thelibraryapp.activities.BooksAndAudioDetailViewActivity
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.SortByBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.SortedBookItemBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.ViewAsBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.YoursBooksPresenter
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.YoursBooksPresenterImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.YourBooksView

import com.padcmyanmar.ttm.thelibraryapp.viewpods.FilterAndSortBookListViewPod
import kotlinx.android.synthetic.main.fragment_your_books.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class YourBooksFragment : Fragment(),YourBooksView {

   //Presenter
    private lateinit var mPresent:YoursBooksPresenter


    //View Pod
    lateinit var mFilterAndSortBookListViewPod: FilterAndSortBookListViewPod

    var readBooksListSortData: List<BooksListVO> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_your_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()

        setUpViewPods()

        mPresent.onUiReady(this)
    }

    private fun setUpPresenter() {
       mPresent = ViewModelProvider(this)[YoursBooksPresenterImpl::class.java]
        mPresent.initView(this)
    }


    private fun setUpViewPods() {
        mFilterAndSortBookListViewPod = vpFilterAndSortBookList as FilterAndSortBookListViewPod
        mFilterAndSortBookListViewPod.setUpBooksListViewPod(mPresent,mPresent)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun showReadBooksList(readBooksListVO: List<BooksListVO>) {
        Log.d("yourbook","check book list ${readBooksListVO.size}")
        readBooksListSortData = readBooksListVO

        mFilterAndSortBookListViewPod.setData(readBooksListVO.sortedWith(compareBy {
            LocalDateTime.parse(
                it.updatedDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }))

    }

    override fun navigateToContextualMenuBottomSheetDialog(booksListVO: BooksListVO?) {
        val customButtonSheet = SortedBookItemBottomSheetDialogFragment(mPresent)
        var args: Bundle = Bundle()
        args.putSerializable("booksItemParamData", booksListVO)
        customButtonSheet.arguments = args

        customButtonSheet.show(requireActivity().supportFragmentManager,"modalSheetDialog")

    }

    override fun viewAsFuncListener(checkedViewAsRadioButtonText: String) {
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun sortFuncListener(checkedViewAsRadioButtonText: String) {
        when (checkedViewAsRadioButtonText) {

            getString(R.string.lbl_title_type) -> {
                mFilterAndSortBookListViewPod.changeSortType(getString(R.string.lbl_title_type))
                mFilterAndSortBookListViewPod.setDataFilterByCategory(readBooksListSortData.sortedBy {
                    it.title
                })
            }
            getString(R.string.lbl_author_type) -> {
                mFilterAndSortBookListViewPod.changeSortType(getString(R.string.lbl_author_type))
                mFilterAndSortBookListViewPod.setDataFilterByCategory(readBooksListSortData.sortedBy {
                    it.author
                })
            }
            else -> {
                mFilterAndSortBookListViewPod.changeSortType(getString(R.string.lbl_recently_opened_type))
                mFilterAndSortBookListViewPod.setDataFilterByCategory( readBooksListSortData.sortedWith(compareBy {
                    LocalDateTime.parse(
                        it.updatedDate,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                }))

            }
        }
    }

    override fun navigateToViewAsBottomSheetDialog(changeListViewType: String) {
        val viewAsBottomSheet = ViewAsBottomSheetDialogFragment(mPresent)
        var args: Bundle = Bundle()
        args.putString("viewType", changeListViewType)
        viewAsBottomSheet.arguments = args
        viewAsBottomSheet.show(requireActivity().supportFragmentManager, "modalSheetDialog")

    }

    override fun navigateToSortByBottomSheetDialog(changeSortType: String) {
        val sortByBottomSheet = SortByBottomSheetDialogFragment(mPresent)
        var args: Bundle = Bundle()
        args.putString("sortByType", changeSortType)
        sortByBottomSheet.arguments = args
        sortByBottomSheet.show(requireActivity().supportFragmentManager, "modalSheetDialog")

    }

    override fun navigateToBookDetailPage(mBooksListVO: BooksListVO?) {
        startActivity(Intent(context, BooksAndAudioDetailViewActivity::class.java))
    }

    override fun callFilterByCategory(chipNameList:ArrayList<String>) {

        if(chipNameList.size == 0)
        {
            readBooksListSortData = mPresent.getAllBooksListNoSelectedCategory()
            mFilterAndSortBookListViewPod.setDataFilterByCategory(mPresent.getAllBooksListNoSelectedCategory())
        }
        else{
            var selectedCategoryListData:ArrayList<BooksListVO> = arrayListOf()
            for (i in chipNameList.indices) {
                mPresent.getAllBooksListFilterByCategory(chipNameList[i])?.let {
                    selectedCategoryListData.addAll(it)
                }
            }
            readBooksListSortData = selectedCategoryListData
            mFilterAndSortBookListViewPod.setDataFilterByCategory(selectedCategoryListData)
        }



    }

    override fun navigateToAddToShelvesList(mBooksListVO: BooksListVO?) {
        startActivity(context?.let { AddToShelvesActivity.newIntent(it, mBooksListVO) })
    }

    override fun showError(errorString: String) {
       Toast.makeText(context,errorString,Toast.LENGTH_SHORT).show()
    }
}