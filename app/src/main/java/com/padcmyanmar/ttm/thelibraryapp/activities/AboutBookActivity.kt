package com.padcmyanmar.ttm.thelibraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.thelibraryapp.R
import kotlinx.android.synthetic.main.activity_about_book.*

class AboutBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_book)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        clickListener()
    }

    private fun clickListener() {
        btnBackAboutBook.setOnClickListener {
            finish()
        }
    }
}