package com.padcmyanmar.ttm.thelibraryapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import com.padcmyanmar.ttm.thelibraryapp.dummy.dummyFilterData
import kotlinx.android.synthetic.main.view_pod_filter_and_sort_book_list.view.*

class FilterAndSortBookListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    lateinit var mSortAndViewAsDelegate: SortAndViewAsDelegate
    lateinit var mBookItemDelegate: BookItemDelegate


    lateinit var mBookListLargeUIViewPod: BookListViewPod
    lateinit var mBookListSmallUIViewPod: BookListViewPod
    lateinit var mBookListUIViewPod: BookListViewPod
    var selectedChipItems: ArrayList<String> = ArrayList()

    var changeListViewType: String = ""
    var changeSortType: String = ""

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setData(delegate: SortAndViewAsDelegate, bookItemDelegateParam: BookItemDelegate) {
        setDelegate(delegate, bookItemDelegateParam)
        setUpUI()

    }

    private fun setUpUI() {
        ivChangeListView.visibility = View.VISIBLE
        setUpViewPods()
        setUpFilterChip()
        clickListener()
    }

    private fun setDelegate(
        delegate: SortAndViewAsDelegate,
        bookItemDelegateParam: BookItemDelegate
    ) {
        this.mSortAndViewAsDelegate = delegate
        this.mBookItemDelegate = bookItemDelegateParam
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

    private fun setUpViewPods() {

        mBookListLargeUIViewPod = vpBookListLargeUI as BookListViewPod
        mBookItemDelegate?.let {
            mBookListLargeUIViewPod.setData(
                it,
                false,
                context.getString(R.string.lbl_large_grid_list)
            )
        }


        mBookListSmallUIViewPod = vpBookListSmallUI as BookListViewPod
        mBookItemDelegate?.let {
            mBookListSmallUIViewPod.setData(
                it,
                false,
                context.getString(R.string.lbl_small_grid_list)
            )
        }

        mBookListUIViewPod = vpBookListUI as BookListViewPod
        mBookItemDelegate?.let {
            mBookListUIViewPod.setData(it, false, context.getString(R.string.lbl_view_list))
        }
        mBookListUIViewPod.visibility = View.VISIBLE
        mBookListLargeUIViewPod.visibility = View.GONE
        mBookListSmallUIViewPod.visibility = View.GONE
        changeListViewType = context.getString(R.string.lbl_view_list)
        changeSortType = context.getString(R.string.lbl_recently_opened_type)


    }

    private fun setUpFilterChip() {
        addItemsInChipGroup()
    }

    private fun addItemsInChipGroup() {

        for (i in dummyFilterData.indices) {
            val entryChip2 = getChip(dummyFilterData[i])
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
                        ivClearFilter.visibility = View.GONE
                    else
                        ivClearFilter.visibility = View.VISIBLE
                }
            }

        }
        return chip
    }

    fun changeListViewType(changeType: String) {
        when (changeType) {

            context.getString(R.string.lbl_large_grid_list) -> {

                mBookListLargeUIViewPod.visibility = View.VISIBLE
                mBookListSmallUIViewPod.visibility = View.GONE
                mBookListUIViewPod.visibility = View.GONE
                ivChangeListView.setImageDrawable(context?.getDrawable(R.drawable.ic_large_grid_gray_64))
                changeListViewType = context.getString(R.string.lbl_large_grid_list)

            }
            context.getString(R.string.lbl_small_grid_list) -> {
                mBookListSmallUIViewPod.visibility = View.VISIBLE
                mBookListLargeUIViewPod.visibility = View.GONE
                mBookListUIViewPod.visibility = View.GONE
                ivChangeListView.setImageDrawable(context?.getDrawable(R.drawable.ic_small_grid_gray_64))
                changeListViewType = context.getString(R.string.lbl_small_grid_list)


            }
            else -> {
                mBookListUIViewPod.visibility = View.VISIBLE
                mBookListLargeUIViewPod.visibility = View.GONE
                mBookListSmallUIViewPod.visibility = View.GONE
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
}