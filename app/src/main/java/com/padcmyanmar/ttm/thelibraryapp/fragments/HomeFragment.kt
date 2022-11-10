package com.padcmyanmar.ttm.thelibraryapp.fragments

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselListener
import alirezat775.lib.carouselview.CarouselView
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.ReadBooksListCarouselAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.SampleModel
import com.padcmyanmar.ttm.thelibraryapp.adapters.HomeTabViewPagerAdapter
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemContextualMenuDelegate
import com.padcmyanmar.ttm.thelibraryapp.dummy.dummyBookTypeList
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    var bookItemContextualMenuDelegate:BookItemContextualMenuDelegate?= null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        bookItemContextualMenuDelegate = context as BookItemContextualMenuDelegate
    }

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
        viewPager.adapter = HomeTabViewPagerAdapter(this)
        viewPager.currentItem = 0
        viewPager.isUserInputEnabled = false

      /*  TabLayoutMediator(tabs, viewPager) { tab, position ->
            when(position)
            {
                0 -> tab.text = "Ebooks"
                1 -> tab.text = "Audiobooks"
                else -> tab.text = "Ebooks"
            }
          //  tab.text = "OBJECT ${(position + 1)}"
        }.attach()*/

        setUpBooksTypeTabLayout()
        setUpCarouselView()
        setUpClickListener()

    }

    private fun setUpClickListener() {
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                dummyBookTypeList?.getOrNull(tab?.position ?: 0)?.let {
                    when(tab?.position)
                    {
                        0 -> viewPager.currentItem = 0
                        1 -> viewPager.currentItem = 2
                        else -> viewPager.currentItem = 0
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setUpBooksTypeTabLayout() {
        dummyBookTypeList.forEach {
            tabs.newTab().apply {
                text = it
                tabs.addTab(this)
            }
        }
    }

    private fun setUpCarouselView() {
/*      var  booksListCarouselAdapter = BooksListCarouselAdapter()
        carouselRecyclerview.adapter = booksListCarouselAdapter*/

        bookItemContextualMenuDelegate?.let {
            val adapter = ReadBooksListCarouselAdapter(it)
            val carousel = Carousel(context as AppCompatActivity, carousel_view, adapter)
            carousel.setOrientation(CarouselView.HORIZONTAL, false)
            // carousel.autoScroll(true, 5000, true)

            carousel.scaleView(true)


            /*  adapter.setOnClickListener(object :
                  SampleAdapter.OnClick {
                  override fun click(model: SampleModel) {
                      carousel.remove(model)
                  }
              })*/
//        carousel.scrollSpeed(100f)
//        carousel.enableSlider(true)

            carousel.addCarouselListener(object : CarouselListener {
                override fun onPositionChange(position: Int) {
                    //  Log.d(TAG, "currentPosition : $position")
                }

                override fun onScroll(dx: Int, dy: Int) {
                    //  Log.d(TAG, "onScroll dx : $dx -- dy : $dx")
                }
            })

//        carousel.add(EmptySampleModel("empty list"))
            carousel.add(SampleModel(1))
            carousel.add(SampleModel(2))
            carousel.add(SampleModel(3))
            carousel.add(SampleModel(4))
            carousel.add(SampleModel(5))
            carousel.add(SampleModel(6))
            carousel.add(SampleModel(7))
            carousel.add(SampleModel(8))
            carousel.add(SampleModel(9))
            carousel.add(SampleModel(10))
        }




    }

}

