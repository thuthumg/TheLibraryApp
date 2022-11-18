package com.padcmyanmar.ttm.thelibraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.TheLibraryAppModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import kotlinx.android.synthetic.main.activity_create_shelf.*

class CreateShelfActivity : AppCompatActivity() {

    private val mTheLibraryAppModel: TheLibraryAppModel = TheLibraryAppModelImpl
    private lateinit var shelfVO:ShelfVO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shelf)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        clickListener()


    }

    private fun clickListener() {
        btnBack.setOnClickListener {
            finish()
        }

        cvDone.setOnClickListener {
            showToast("Done btn click")
            val randomId = (0..100).random()
            var booksList:List<BooksListVO> = listOf()
            shelfVO = ShelfVO(randomId,txtInputEditTextShelf.text.toString(),booksList)

            mTheLibraryAppModel.insertShelf(shelfVO,
                onSuccess = {
                    showToast(it)
                    finish()
                },
                onFailure = {
                    showToast(it)
                })
        }
    }

    private fun showToast(it: String) {

        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()


    }


}