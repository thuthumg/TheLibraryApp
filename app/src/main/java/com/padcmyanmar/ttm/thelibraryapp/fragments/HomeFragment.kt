package com.padcmyanmar.ttm.thelibraryapp.fragments

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselListener
import alirezat775.lib.carouselview.CarouselView
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.ReadBooksListCarouselAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.SampleModel
import com.padcmyanmar.ttm.thelibraryapp.adapters.TabViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {


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
        viewPager.adapter = TabViewPagerAdapter(this)
        viewPager.currentItem = 0
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            when(position)
            {
                0 -> tab.text = "Ebooks"
                1 -> tab.text = "Audiobooks"
                else -> tab.text = "Ebooks"
            }
          //  tab.text = "OBJECT ${(position + 1)}"
        }.attach()

        setUpCarouselView()

    }

    private fun setUpCarouselView() {
/*      var  booksListCarouselAdapter = BooksListCarouselAdapter()
        carouselRecyclerview.adapter = booksListCarouselAdapter*/


        val adapter = ReadBooksListCarouselAdapter()
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

