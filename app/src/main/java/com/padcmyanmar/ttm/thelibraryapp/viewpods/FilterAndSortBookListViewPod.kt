package com.padcmyanmar.ttm.thelibraryapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.LargeBooksListAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.SmallBooksListAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.ViewTypeListBookListAdapter
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import kotlinx.android.synthetic.main.view_pod_filter_and_sort_book_list.view.*

class FilterAndSortBookListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private lateinit var mSortAndViewAsDelegate: SortAndViewAsDelegate
    lateinit var mBookItemDelegate: BookItemDelegate

    lateinit var mViewTypeListBookListAdapter:ViewTypeListBookListAdapter
    lateinit var mLargeBooksListAdapter: LargeBooksListAdapter
    lateinit var mSmallBooksListAdapter: SmallBooksListAdapter



    var selectedChipItems: ArrayList<String> = ArrayList()

    var changeListViewType: String = ""
    var changeSortType: String = ""


    //for filter chip
    private var mCategoryFilterName:ArrayList<String> = arrayListOf()

    var mBooksListVoListToSort: List<BooksListVO> = listOf()

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setUpBooksListViewPod(delegate: SortAndViewAsDelegate, bookItemDelegateParam: BookItemDelegate) {
        setDelegate(delegate, bookItemDelegateParam)
        setUpUI()

    }

    private fun setDelegate(
        delegate: SortAndViewAsDelegate,
        bookItemDelegateParam: BookItemDelegate
    ) {
        this.mSortAndViewAsDelegate = delegate
        this.mBookItemDelegate = bookItemDelegateParam
    }
    private fun setUpUI() {

        changeListViewType = context.getString(R.string.lbl_view_list)
        changeSortType = context.getString(R.string.lbl_recently_opened_type)
        setUpBooksListAdapter(this.mBookItemDelegate,false)
        clickListener()
    }

    private fun clickListener() {
        ivClearFilter.setOnClickListener {
            selectedChipItems.clear()
            chipGroupFilter.clearCheck()
        }

        ivChangeListView.setOnClickListener {

            mSortAndViewAsDelegate.callViewAsBottomSheetDialog(changeListViewType)


        }

        ivChangeSortType.setOnClickListener {

            mSortAndViewAsDelegate.callSortByBottomSheetDialog(changeSortType)


        }


    }

    private fun setUpFilterChip() {
        addItemsInChipGroup()
    }

    private fun addItemsInChipGroup() {

        for (i in mCategoryFilterName.indices) {
            val entryChip2 = getChip(mCategoryFilterName[i])
             entryChip2.id = i
            chipGroupFilter.addView(entryChip2)
        }
    }

    private fun getChip(text: String): Chip {

        val chip = Chip(context)
        context?.let {
            chip.setChipDrawable(ChipDrawable.createFromResource(it, R.xml.filter_chip))

        }
        val paddingDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 50f,
            resources.displayMetrics
        ).toInt()
        chip.setChipBackgroundColorResource(R.color.chip_unselected_color)
        chip.chipStrokeWidth = 1F
        chip.setChipStrokeColorResource(R.color.whiteDark5)

        chip.isCloseIconVisible = false
        chip.isCheckedIconVisible = false
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
        chip.text = text
        chip.setOnCheckedChangeListener { buttonView, isChecked ->
            context?.let {
                if (isChecked) {
                    chip.chipBackgroundColor =
                        AppCompatResources.getColorStateList(it, R.color.chip_selected_color)
                    chip.setTextColor(resources.getColor(R.color.white))
                    chip.isChecked = true
                    selectedChipItems.add(chip.text.toString())
                    ivClearFilter.visibility = View.VISIBLE



                } else {
                    chip.chipBackgroundColor =
                        AppCompatResources.getColorStateList(it, R.color.chip_unselected_color)
                    chip.chipStrokeWidth = 1F
                    chip.chipStrokeColor=AppCompatResources.getColorStateList(it, R.color.whiteDark5)
                    chip.setTextColor(resources.getColor(R.color.black))
                    chip.isChecked = false
                    selectedChipItems.remove(chip.text.toString())

                    if (selectedChipItems.size <= 0)
                    {

                        ivClearFilter.visibility = View.GONE
                    }

                    else
                    {
                        ivClearFilter.visibility = View.VISIBLE

                    }

                }

                mSortAndViewAsDelegate.callFilterByCategory(selectedChipItems)

            }

        }
        return chip
    }

    fun changeListViewType(changeType: String) {
        when (changeType) {

            context.getString(R.string.lbl_large_grid_list) -> {

                rvBookListLargeUI.visibility = View.VISIBLE
                rvBookListSmallUI.visibility = View.GONE
                rvBookListUI.visibility = View.GONE
                ivChangeListView.setImageDrawable(context?.getDrawable(R.drawable.ic_large_grid_gray_64))
                changeListViewType = context.getString(R.string.lbl_large_grid_list)

            }
            context.getString(R.string.lbl_small_grid_list) -> {
                rvBookListSmallUI.visibility = View.VISIBLE
                rvBookListLargeUI.visibility = View.GONE
                rvBookListUI.visibility = View.GONE
                ivChangeListView.setImageDrawable(context?.getDrawable(R.drawable.ic_small_grid_gray_64))
                changeListViewType = context.getString(R.string.lbl_small_grid_list)


            }
            else -> {
                rvBookListUI.visibility = View.VISIBLE
                rvBookListLargeUI.visibility = View.GONE
                rvBookListSmallUI.visibility = View.GONE
                ivChangeListView.setImageDrawable(context?.getDrawable(R.drawable.ic_list_gray_64))
                changeListViewType = context.getString(R.string.lbl_view_list)
            }
        }
    }

    fun changeSortType(changeSortByType: String) {
        when (changeSortByType) {

            context.getString(R.string.lbl_title_type) -> {

                tvSortTypeName.text = String.format( context.getString(R.string.lbl_sort_by),
                    context.getString(R.string.lbl_title_type))
                changeSortType =  context.getString(R.string.lbl_title_type)

            }
            context.getString(R.string.lbl_author_type) -> {

                tvSortTypeName.text = String.format( context.getString(R.string.lbl_sort_by),
                    context.getString(R.string.lbl_author_type))
                changeSortType =  context.getString(R.string.lbl_author_type)

            }
            else -> {

                tvSortTypeName.text = String.format( context.getString(R.string.lbl_sort_by),
                    context.getString(R.string.lbl_recently_opened_type))
                changeSortType =  context.getString(R.string.lbl_recently_opened_type)
            }
        }
    }


    private fun setUpBooksListAdapter(delegate: BookItemDelegate,
                                      checkAudioOrEbooksFlagParam: Boolean) {

        rvBookListUI.visibility = View.VISIBLE
        rvBookListSmallUI.visibility = View.GONE
        rvBookListLargeUI.visibility = View.GONE

        largeGridViewUI(delegate,checkAudioOrEbooksFlagParam)
        smallGridViewUI(delegate,checkAudioOrEbooksFlagParam)
        listViewUI(delegate,checkAudioOrEbooksFlagParam)

    }

    private fun listViewUI(
        delegate: BookItemDelegate,
        checkAudioOrEbooksFlagParam: Boolean
    ) {


        mViewTypeListBookListAdapter = ViewTypeListBookListAdapter(delegate,checkAudioOrEbooksFlagParam)
        rvBookListUI.adapter = mViewTypeListBookListAdapter
        rvBookListUI.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false)
        rvBookListUI.isNestedScrollingEnabled = false
    }

    private fun smallGridViewUI(
        delegate: BookItemDelegate,
        checkAudioOrEbooksFlagParam: Boolean
    ) {

        mSmallBooksListAdapter = SmallBooksListAdapter(delegate,checkAudioOrEbooksFlagParam)
        rvBookListSmallUI.adapter = mSmallBooksListAdapter
        rvBookListSmallUI.layoutManager = GridLayoutManager(
            context,
            3,
            LinearLayoutManager.VERTICAL, false)
        rvBookListSmallUI.isNestedScrollingEnabled = false
    }

    private fun largeGridViewUI(
        delegate: BookItemDelegate,
        checkAudioOrEbooksFlagParam: Boolean
    ) {

        mLargeBooksListAdapter = LargeBooksListAdapter(delegate,checkAudioOrEbooksFlagParam)
        rvBookListLargeUI.adapter = mLargeBooksListAdapter
        rvBookListLargeUI.layoutManager = GridLayoutManager(
            context,
            2,
            LinearLayoutManager.VERTICAL, false)
        rvBookListLargeUI.isNestedScrollingEnabled = false
    }

    fun setData(mBooksListVoList: List<BooksListVO>){
        mBooksListVoListToSort = mBooksListVoList
        mViewTypeListBookListAdapter.setNewData(mBooksListVoList)
        mLargeBooksListAdapter.setNewData(mBooksListVoList)
        mSmallBooksListAdapter.setNewData(mBooksListVoList)

        var listData =mBooksListVoList.distinctBy { it.categoryName }


        for (i in listData.indices) {
             mCategoryFilterName.add(listData[i].categoryName.toString())
        }

        setUpFilterChip()

    }

    fun setDataFilterByCategory(mBooksListVoList: List<BooksListVO>){

        Log.d("filter","check filter data ${mBooksListVoList.size}")
        mBooksListVoListToSort = mBooksListVoList
        mViewTypeListBookListAdapter.setNewData(mBooksListVoList)
        mLargeBooksListAdapter.setNewData(mBooksListVoList)
        mSmallBooksListAdapter.setNewData(mBooksListVoList)
    }
}