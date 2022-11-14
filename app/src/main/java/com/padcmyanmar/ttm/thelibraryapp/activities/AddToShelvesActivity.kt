package com.padcmyanmar.ttm.thelibraryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate

import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.AddToShelvesListAdapter
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO

import com.padcmyanmar.ttm.thelibraryapp.delegates.BookItemDelegate
import kotlinx.android.synthetic.main.activity_add_to_shelves.*


class AddToShelvesActivity : AppCompatActivity(),BookItemDelegate {

    lateinit var mAddToShelvesList:AddToShelvesListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_shelves)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setUpAddToShelvesList()
        clickListener()
    }

    private fun clickListener() {
       ivClose.setOnClickListener {
           finish()
       }
    }

    private fun setUpAddToShelvesList() {
        mAddToShelvesList = AddToShelvesListAdapter(this,false)
        rvShelvesBooksItemList.adapter = mAddToShelvesList
        rvShelvesBooksItemList.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        rvShelvesBooksItemList.isNestedScrollingEnabled = false
    }

    override fun callContextualMenuBottomSheetDialogFun() {

    }

    override fun callMoreFunc() {

    }

    override fun callBookDetailPage(mBooksListVO: BooksListVO?) {
        startActivity(Intent(this,BooksAndAudioDetailViewActivity::class.java))
    }
}