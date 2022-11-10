package com.padcmyanmar.ttm.thelibraryapp.activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemContextualMenuDelegate
import com.padcmyanmar.ttm.thelibraryapp.fragments.BookItemBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.fragments.HomeFragment
import com.padcmyanmar.ttm.thelibraryapp.fragments.LibraryFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_holder_unread_books_list.view.*


class MainActivity : AppCompatActivity(),BookItemContextualMenuDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

     //   val homeFragment = HomeFragment()
     //   val libraryFragment = LibraryFragment()

      //  setCurrentFragment(homeFragment)

        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.flFragment, fragment, fragment.javaClass.simpleName)
            .commit()

        bottomNavigation.setOnItemSelectedListener {
            Log.d("mainact","check fragment item selected")
            when(it.itemId){
                R.id.action_home -> {
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.flFragment,fragment,fragment.javaClass.simpleName
                    ).commit()

                    return@setOnItemSelectedListener true
                }
                R.id.action_library -> {
                    val fragment = LibraryFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.flFragment,fragment,fragment.javaClass.simpleName
                    ).commit()

                    return@setOnItemSelectedListener true
                }
            }
            false

        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        Log.d("mainact","check fragment ${fragment.id}")
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
    }

    override fun callBottomSheetDialogFun() {
       val customButtonSheet = BookItemBottomSheetDialogFragment()
        customButtonSheet.show(supportFragmentManager,"modalSheetDialog")
    }

    override fun callMoreFunc() {
        startActivity(Intent(this,BooksListMoreActivity::class.java))
    }

}