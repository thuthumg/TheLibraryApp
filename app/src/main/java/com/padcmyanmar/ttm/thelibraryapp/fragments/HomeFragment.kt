package com.padcmyanmar.ttm.thelibraryapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.BooksListCarouselAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.TabViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

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
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()

        setUpCarouselView()

    }

    private fun setUpCarouselView() {
      var  booksListCarouselAdapter = BooksListCarouselAdapter()
        carouselRecyclerview.adapter = booksListCarouselAdapter
    }

}

