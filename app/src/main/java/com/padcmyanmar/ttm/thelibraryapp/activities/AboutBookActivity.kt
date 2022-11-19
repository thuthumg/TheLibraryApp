package com.padcmyanmar.ttm.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import kotlinx.android.synthetic.main.activity_about_book.*

class AboutBookActivity : AppCompatActivity() {

    var mDesc:String = ""
    companion object {
        private const val BOOKS_DESC = "BOOKS_DESC"
        // private const val CATEGORY_NAME_ID = "CATEGORY_NAME_ID"
        fun newIntent(context: Context, description:String): Intent {
            val intent = Intent(context, AboutBookActivity::class.java)
            //  intent.putExtra(CATEGORY_NAME_ID,categoryName)
            intent.putExtra(BOOKS_DESC, description)

            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_book)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getIntentParam()
        bindData()
        clickListener()
    }

    private fun bindData() {
        tvDesc.text = mDesc
    }

    private fun getIntentParam() {
        val intent = intent
        mDesc = intent.getStringExtra(BOOKS_DESC).toString()
    }
    private fun clickListener() {
        btnBackAboutBook.setOnClickListener {
            finish()
        }


    }
}