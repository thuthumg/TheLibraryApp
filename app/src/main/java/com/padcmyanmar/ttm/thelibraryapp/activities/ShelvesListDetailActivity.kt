package com.padcmyanmar.ttm.thelibraryapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.*
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.ShelvesListDetailPresenter
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.ShelvesListDetailPresenterImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.ShelvesListDetailView
import com.padcmyanmar.ttm.thelibraryapp.viewpods.FilterAndSortBookListViewPod
import kotlinx.android.synthetic.main.activity_shelves_list.*
import kotlinx.android.synthetic.main.fragment_your_books.vpFilterAndSortBookList
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ShelvesListDetailActivity : AppCompatActivity(), ShelvesListDetailView{//, SortAndViewAsDelegate, BookItemDelegate

    //mPresenter
    private lateinit var mPresent:ShelvesListDetailPresenter

    lateinit var mFilterAndSortBookListViewPod: FilterAndSortBookListViewPod
    var shelfName:String = ""

    var mShelfVO:ShelfVO?= null

    companion object {
        private const val SHELVES_NAME_ID = "SHELVES_NAME_ID"
        private const val BOOK_LIST_VO = "BOOK_LIST_VO"
        fun newIntent(context: Context?, shelfName: String, mShelfVO: ShelfVO?): Intent {
            val intent = Intent(context, ShelvesListDetailActivity::class.java)
            intent.putExtra(SHELVES_NAME_ID, shelfName)
            intent.putExtra(BOOK_LIST_VO , mShelfVO)

            return intent
        }
    }

    var readBooksListSortData: List<BooksListVO> = listOf()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelves_list)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setUpPresenter()

        getIntentData()
        setUpViewPods()
        setUpBottomSheet()
        clickListener()

        mPresent.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresent = ViewModelProvider(this)[ShelvesListDetailPresenterImpl::class.java]
        mPresent.initView(this)
    }

    private fun clickListener() {
       btnBack.setOnClickListener {
           finish()
       }
    }

    private fun setUpBottomSheet() {
        btnContextualMenu.setOnClickListener {
            val shelvesListMenuBottomSheetDialogFragment = ShelvesListMenuBottomSheetDialogFragment(mPresent)
            var args: Bundle = Bundle()
            args.putString("shelvesNameId", tvShelvesName.text.toString())
            mShelfVO?.id?.let { it1 -> args.putInt("shelvesId", it1) }
            shelvesListMenuBottomSheetDialogFragment.arguments = args
            shelvesListMenuBottomSheetDialogFragment.show(supportFragmentManager, "modalSheetDialog")

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getIntentData() {
        shelfName = intent?.getStringExtra(SHELVES_NAME_ID).toString()
        mShelfVO = intent?.getSerializableExtra(BOOK_LIST_VO) as ShelfVO


        readBooksListSortData = mShelfVO!!.booksList!!

        tvShelvesName.visibility = View.VISIBLE
       tvShelvesName.text = shelfName


    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    private fun setUpViewPods() {
        mFilterAndSortBookListViewPod = vpFilterAndSortBookList as FilterAndSortBookListViewPod
            mFilterAndSortBookListViewPod.setUpBooksListViewPod(mPresent,mPresent)


        mFilterAndSortBookListViewPod.setData(readBooksListSortData.sortedWith(compareBy {
            LocalDateTime.parse(
                it.updatedDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        }))
    }
    override fun navigateToContextualMenuBottomSheetDialog(mBooksListVO: BooksListVO?) {
        val customButtonSheet = SortedBookItemBottomSheetDialogFragment(mPresent)
        var args: Bundle = Bundle()
        args.putSerializable("booksItemParamData", mBooksListVO)
        customButtonSheet.arguments = args
        customButtonSheet.show(supportFragmentManager,"modalSheetDialog")

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
        viewAsBottomSheet.show(supportFragmentManager, "modalSheetDialog")

    }

    override fun navigateToSortByBottomSheetDialog(changeSortType: String) {
        val sortByBottomSheet = SortByBottomSheetDialogFragment(mPresent)
        var args: Bundle = Bundle()
        args.putString("sortByType", changeSortType)
        sortByBottomSheet.arguments = args
        sortByBottomSheet.show(supportFragmentManager, "modalSheetDialog")

    }

    override fun navigateToBookDetailPage(mBooksListVO: BooksListVO?) {
        startActivity(Intent(this, BooksAndAudioDetailViewActivity::class.java))

    }

    override fun callFilterByCategory(chipNameList: ArrayList<String>) {

        if(chipNameList.size == 0)
        {
            mFilterAndSortBookListViewPod.setDataFilterByCategory(readBooksListSortData)
        }
        else{
            var selectedCategoryListData:ArrayList<BooksListVO> = arrayListOf()
            for (i in chipNameList.indices) {

                    readBooksListSortData.forEach {
                        if(it.categoryName.equals(chipNameList[i]))
                        {
                            selectedCategoryListData.add(it)
                        }
                    }

            }
            mFilterAndSortBookListViewPod.setDataFilterByCategory(selectedCategoryListData)
        }

    }
    override fun showError(errorString: String) {
        Toast.makeText(this,errorString, Toast.LENGTH_SHORT).show()

    }
    override fun changeToShelfNameUI() {
        tvShelvesName.visibility = View.GONE
        etShelvesName.visibility = View.VISIBLE
        etShelvesName.setText(shelfName)
        etShelvesName.setOnEditorActionListener(TextView.OnEditorActionListener{ _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {

                // Do something of your interest.
                // We in this examples created the following Toasts
//                if(etShelvesName.text.toString() == "geeksforgeeks"){
//                    Toast.makeText(applicationContext, "Welcome to GFG", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(applicationContext, "Invalid Input", Toast.LENGTH_SHORT).show()
//                }
                mShelfVO?.shelfName = etShelvesName.text.toString()

                //to update that data in db
                mShelfVO?.let { mPresent.saveShelfData(it) }


                finish()



                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun deleteShelf(shelfId: Int) {
        mPresent.deleteShelfData(shelfId)
        finish()
    }

    override fun showShelvesListData(shelvesList: List<ShelfVO>) {

    }

    override fun navigateToShelfListPage(textShelfName: String) {

    }
    override fun showReadBooksList(readBooksListVO: List<BooksListVO>) {
    }



}