package com.padcmyanmar.ttm.thelibraryapp.activities


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.fragments.HomeFragment
import com.padcmyanmar.ttm.thelibraryapp.fragments.LibraryFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val libraryFragment = LibraryFragment()

        setCurrentFragment(homeFragment)

        bottomNavigation.setOnClickListener {
            when(it.id){
                R.id.action_home -> setCurrentFragment(homeFragment)
                R.id.action_library -> setCurrentFragment(libraryFragment)
            }

            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
        }
    }


}