package com.padcmyanmar.ttm.thelibraryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.HomeTabViewPagerAdapter
import com.padcmyanmar.ttm.thelibraryapp.dummy.dummyBookTypeListOfUser
import kotlinx.android.synthetic.main.fragment_library.*


class LibraryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = HomeTabViewPagerAdapter(this)
        viewPager.currentItem = 0
        viewPager.isUserInputEnabled = false
        setUpBooksTypeTabLayout()
        setUpClickListener()

    }
    private fun setUpBooksTypeTabLayout() {
        dummyBookTypeListOfUser.forEach {
            tlForBooksAndShelves.newTab().apply {
                text = it
                tlForBooksAndShelves.addTab(this)
            }
        }
    }

    private fun setUpClickListener() {
        tlForBooksAndShelves.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                dummyBookTypeListOfUser?.getOrNull(tab?.position ?: 0)?.let {
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


}