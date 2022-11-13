package com.padcmyanmar.ttm.thelibraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.models.ShelvesDataModel
import com.padcmyanmar.ttm.thelibraryapp.data.models.ShelvesDataModelImpl
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import kotlinx.android.synthetic.main.activity_create_shelf.*
import kotlinx.android.synthetic.main.fragment_your_shelves.*

class CreateShelfActivity : AppCompatActivity() {

    private val mShelvesDataModel: ShelvesDataModel = ShelvesDataModelImpl

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

            mShelvesDataModel.insertShelf(txtInputEditTextShelf.text.toString(),
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