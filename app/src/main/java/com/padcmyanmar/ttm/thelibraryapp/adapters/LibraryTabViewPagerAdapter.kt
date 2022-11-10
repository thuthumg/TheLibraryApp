package com.padcmyanmar.ttm.thelibraryapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.padcmyanmar.ttm.thelibraryapp.fragments.AudioBooksFragment
import com.padcmyanmar.ttm.thelibraryapp.fragments.EBooksFragment

class LibraryTabViewPagerAdapter (fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0-> return EBooksFragment()
            1-> return AudioBooksFragment()

        }
        return EBooksFragment()

    }

}