package com.padcmyanmar.ttm.thelibraryapp.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.GooglePlayBookAdapter
import com.padcmyanmar.ttm.thelibraryapp.dummy.dummyBookTypeList
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : AppCompatActivity() {

    lateinit var mGooglePlayEBookAdapter: GooglePlayBookAdapter
    lateinit var mGooglePlayAudioBookAdapter: GooglePlayBookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //book type
        setUpBooksTypeTabLayout()
        setUpPlayBookAdapter()
        rvEbookGooglePlayBook.visibility = View.VISIBLE
        rvAudiobookGooglePlayBook.visibility = View.GONE
        clickListener()

    }

    private fun clickListener() {

        btnBackSearch.setOnClickListener {
            finish()
        }
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                dummyBookTypeList?.getOrNull(tab?.position ?: 0)?.let {
                    when(tab?.position)
                    {
                        0 -> {
                            rvEbookGooglePlayBook.visibility = View.VISIBLE
                            rvAudiobookGooglePlayBook.visibility = View.GONE
                        }
                        1 -> {
                            rvEbookGooglePlayBook.visibility = View.GONE
                            rvAudiobookGooglePlayBook.visibility = View.VISIBLE
                        }
                        else -> {
                            rvEbookGooglePlayBook.visibility = View.VISIBLE
                            rvAudiobookGooglePlayBook.visibility = View.GONE
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

    private fun setUpBooksTypeTabLayout() {
        dummyBookTypeList.forEach {
            tabs.newTab().apply {
                text = it
                tabs.addTab(this)
            }
        }
    }

    private fun setUpPlayBookAdapter() {
        mGooglePlayEBookAdapter = GooglePlayBookAdapter()
        rvEbookGooglePlayBook.adapter = mGooglePlayEBookAdapter
        rvEbookGooglePlayBook.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false)

        rvEbookGooglePlayBook.isNestedScrollingEnabled = false

        mGooglePlayAudioBookAdapter = GooglePlayBookAdapter()
        rvAudiobookGooglePlayBook.adapter = mGooglePlayAudioBookAdapter
        rvAudiobookGooglePlayBook.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false)

        rvAudiobookGooglePlayBook.isNestedScrollingEnabled = false
    }
}