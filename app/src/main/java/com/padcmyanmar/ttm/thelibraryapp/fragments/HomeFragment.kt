package com.padcmyanmar.ttm.thelibraryapp.fragments

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselListener
import alirezat775.lib.carouselview.CarouselModel
import alirezat775.lib.carouselview.CarouselView
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.AddToShelvesActivity
import com.padcmyanmar.ttm.thelibraryapp.activities.BooksAndAudioDetailViewActivity
import com.padcmyanmar.ttm.thelibraryapp.activities.EachCategoryBookListActivity
import com.padcmyanmar.ttm.thelibraryapp.adapters.EBooksAndAudioBooksListAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.ReadBooksListCarouselAdapter

import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.BookItemBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.CategoryBooksListVO
import com.padcmyanmar.ttm.thelibraryapp.dummy.dummyBookTypeList
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.HomePresenter
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.HomePresenterImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.HomeView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_pod_ebook_list.view.*

class HomeFragment : Fragment(), HomeView{


    //Presenter
    private lateinit var mPresenter: HomePresenter
    private var mCategoryBooksList: List<CategoryBooksListVO>? = null

    private lateinit var mEBooksAndAudioBooksListAdapter: EBooksAndAudioBooksListAdapter
    lateinit var carousel:Carousel;
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
     val viewFragmentHome =  inflater.inflate(R.layout.fragment_home, container, false)
        return viewFragmentHome.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()

       // setUpViewPager()
        //book type
        setUpBooksTypeTabLayout()

        //read book list
        setUpCarouselView()

        //ForEbooks And AudioBooks View
        setUpRecyclerView()
        setUpClickListener()

        mPresenter.onUiReady(this)

    }
    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[HomePresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpRecyclerView() {

        mEBooksAndAudioBooksListAdapter = EBooksAndAudioBooksListAdapter(mPresenter)
        rvEBooksAndAudioBooksList.adapter = mEBooksAndAudioBooksListAdapter
        rvEBooksAndAudioBooksList.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false        )

        rvEBooksAndAudioBooksList.isNestedScrollingEnabled = false

        rvEBooksAndAudioBooksList.visibility = View.VISIBLE
    }

    private fun setUpBooksTypeTabLayout() {
        dummyBookTypeList.forEach {
            tabs.newTab().apply {
                text = it
                tabs.addTab(this)
            }
        }
    }

    private fun setUpClickListener() {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                dummyBookTypeList?.getOrNull(tab?.position ?: 0)?.let {
                    when(tab?.position)
                    {
                        0 -> {
                            rvEBooksAndAudioBooksList.visibility = View.VISIBLE
                        }
                        1 -> {
                            rvEBooksAndAudioBooksList.visibility = View.GONE
                        }
                        else -> {
                            rvEBooksAndAudioBooksList.visibility = View.VISIBLE
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setUpCarouselView() {

      //  bookItemDelegate?.let {
            val adapter = ReadBooksListCarouselAdapter(mPresenter)
            carousel = Carousel(context as AppCompatActivity, carouselView, adapter)
            carousel.setOrientation(CarouselView.HORIZONTAL, false)
            // carousel.autoScroll(true, 5000, true)

            carousel.scaleView(true)
            carousel.addCarouselListener(object : CarouselListener {
                override fun onPositionChange(position: Int) {
                    //  Log.d(TAG, "currentPosition : $position")
                }

                override fun onScroll(dx: Int, dy: Int) {
                    //  Log.d(TAG, "onScroll dx : $dx -- dy : $dx")
                }
            })

    }

    override fun showCategoryBooksList(categoryBooksList: List<CategoryBooksListVO>) {
        mCategoryBooksList = categoryBooksList
        mEBooksAndAudioBooksListAdapter.setNewData(categoryBooksList)
    }

    override fun navigateToContextualMenuBottomSheetDialog(mBooksListVO: BooksListVO?) {
        val customButtonSheet = BookItemBottomSheetDialogFragment(mPresenter)
        var args: Bundle = Bundle()
        args.putSerializable("booksItemParamData", mBooksListVO)
        customButtonSheet.arguments = args
        customButtonSheet.show(requireActivity().supportFragmentManager,"modalSheetDialog")

    }

    override fun navigateToBooksMorePage(categoryName: String,categoryId:Int) {
        startActivity(context?.let { EachCategoryBookListActivity.newIntent(it,categoryName,categoryId) })
    }


    override fun navigateToBookDetailPage(mBooksListVO: BooksListVO?) {


        context?.let { context->
            mBooksListVO?.let { booksListVO ->
                startActivity(BooksAndAudioDetailViewActivity.newIntent(context,booksListVO))
            }
        }

    }

    override fun showReadBooksList(readBooksListVO: List<BooksListVO>) {

        if(readBooksListVO.isEmpty())
        {
            llEmptyView.visibility = View.VISIBLE
            carouselView.visibility = View.GONE
        }else{
            llEmptyView.visibility = View.GONE
            carouselView.visibility = View.VISIBLE

            var readMutableList:MutableList<CarouselModel> = arrayListOf()
            readBooksListVO.forEach {
                readMutableList.add(it)
                // carousel.add(it)
            }

            carousel.addAll(readMutableList)
        }



    }

    override fun navigateToAddToShelvesList(mBooksListVO: BooksListVO?) {

//        outerLoop@ mCategoryBooksList?.forEach {
//            it.books?.forEach { booksVO->
//                if(booksVO.id == mBooksListVO?.id)
//                {
//                    mBooksListVO?.categoryName = it.listName
//                    mBooksListVO?.categoryId = it.listId
//
//                    break@outerLoop
//                }
//            }
//        }


        startActivity(context?.let { AddToShelvesActivity.newIntent(it, mBooksListVO) })

    }

    override fun showError(errorString: String) {
       Toast.makeText(context,errorString,Toast.LENGTH_SHORT).show()
    }

}

