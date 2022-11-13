package com.padcmyanmar.ttm.thelibraryapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.padcmyanmar.ttm.thelibraryapp.fragments.YourBooksFragment
import com.padcmyanmar.ttm.thelibraryapp.fragments.YourShelvesFragment

class LibraryTabViewPagerAdapter (fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0-> return YourBooksFragment()
            1-> return YourShelvesFragment()

        }
        return YourBooksFragment()

    }

}