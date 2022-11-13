package com.padcmyanmar.ttm.thelibraryapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.*
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import com.padcmyanmar.ttm.thelibraryapp.viewpods.FilterAndSortBookListViewPod
import kotlinx.android.synthetic.main.activity_shelves_list.*
import kotlinx.android.synthetic.main.fragment_your_books.vpFilterAndSortBookList

class ShelvesListActivity : AppCompatActivity(), SortAndViewAsDelegate, BookItemDelegate {

    lateinit var mFilterAndSortBookListViewPod: FilterAndSortBookListViewPod
    var shelfName:String = ""
    companion object {
        private const val SHELVES_NAME_ID = "SHELVES_NAME_ID"

        fun newIntent(context: Context, shelfName: String): Intent {
            val intent = Intent(context, ShelvesListActivity::class.java)
            intent.putExtra(SHELVES_NAME_ID, shelfName)

            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelves_list)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getIntentData()
        setUpViewPods()
        setUpBottomSheet()
        clickListener()
    }

    private fun clickListener() {
       btnBack.setOnClickListener {
           finish()
       }
    }

    private fun setUpBottomSheet() {
        btnContextualMenu.setOnClickListener {
            val shelvesListMenuBottomSheetDialogFragment = ShelvesListMenuBottomSheetDialogFragment()
            var args: Bundle = Bundle()
            args.putString("shelvesNameId", tvShelvesName.text.toString())
            shelvesListMenuBottomSheetDialogFragment.arguments = args
            shelvesListMenuBottomSheetDialogFragment.show(supportFragmentManager, "modalSheetDialog")

        }
    }

    private fun getIntentData() {
        shelfName = intent?.getStringExtra(SHELVES_NAME_ID).toString()
       tvShelvesName.text = shelfName
    }


    @SuppressLint("SuspiciousIndentation")
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
        viewAsBottomSheet.show(supportFragmentManager, "modalSheetDialog")
    }

    override fun callSortByBottomSheetDialog(changeSortType: String) {
        val sortByBottomSheet = SortByBottomSheetDialogFragment(this)
        var args: Bundle = Bundle()
        args.putString("sortByType", changeSortType)
        sortByBottomSheet.arguments = args
        sortByBottomSheet.show(supportFragmentManager, "modalSheetDialog")
    }

    override fun callContextualMenuBottomSheetDialogFun() {
        val customButtonSheet = SortedBookItemBottomSheetDialogFragment()
        customButtonSheet.show(supportFragmentManager,"modalSheetDialog")
    }

    override fun callMoreFunc() {
        startActivity(Intent(this,EachCategoryBookListActivity::class.java))
    }

    override fun callBookDetailPage() {
        startActivity(Intent(this,BooksAndAudioDetailViewActivity::class.java))
    }


}