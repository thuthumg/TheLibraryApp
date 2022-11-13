package com.padcmyanmar.ttm.thelibraryapp.activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import com.padcmyanmar.ttm.thelibraryapp.delegates.SortAndViewAsDelegate
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.BookItemBottomSheetDialogFragment
import com.padcmyanmar.ttm.thelibraryapp.fragments.HomeFragment
import com.padcmyanmar.ttm.thelibraryapp.fragments.LibraryFragment
import com.padcmyanmar.ttm.thelibraryapp.bottomSheetDialog.ViewAsBottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setUpBottomNavUI()
        clickListener()

    }

    private fun clickListener() {
        toolBarSearch.setOnClickListener {
            startActivity(Intent(this,SearchActivity::class.java))
        }
    }

    private fun setUpBottomNavUI() {
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.flFragment, fragment, fragment.javaClass.simpleName)
            .commit()

        bottomNavigation.setOnItemSelectedListener {

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

//    override fun callContextualMenuBottomSheetDialogFun() {
//       val customButtonSheet = BookItemBottomSheetDialogFragment()
//        customButtonSheet.show(supportFragmentManager,"modalSheetDialog")
//    }

//    override fun callMoreFunc() {
//        startActivity(Intent(this,BooksListMoreActivity::class.java))
//    }




}