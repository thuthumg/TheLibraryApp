package com.padcmyanmar.ttm.thelibraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.CreateShelfPresenter
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.CreateShelfPresenterImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.CreateShelfView
import kotlinx.android.synthetic.main.activity_create_shelf.*
import kotlinx.android.synthetic.main.activity_create_shelf.btnBack

class CreateShelfActivity : AppCompatActivity(),CreateShelfView {


    //mPresenter
    private lateinit var mPresenter:CreateShelfPresenter

    private lateinit var shelfVO:ShelfVO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shelf)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setUpPresenter()
        clickListener()
        mPresenter.onUiReady(this)

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[CreateShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun clickListener() {
        btnBack.setOnClickListener {
           mPresenter?.callToBack()
        }

        cvDone.setOnClickListener {

            val randomId = (0..100).random()
            var booksList:List<BooksListVO> = listOf()
            shelfVO = ShelfVO(randomId,txtInputEditTextShelf.text.toString(),booksList)

            mPresenter?.createShelfData(shelfVO)
            finish()
          /*  mTheLibraryAppModel.insertShelf(shelfVO,
                onSuccess = {
                    showToast(it)
                    finish()
                },
                onFailure = {
                    showToast(it)
                })*/
        }

        txtInputEditTextShelf.setOnEditorActionListener(TextView.OnEditorActionListener{ _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {

                val randomId = (0..100).random()
                var booksList:List<BooksListVO> = listOf()
                shelfVO = ShelfVO(randomId,txtInputEditTextShelf.text.toString(),booksList)

              /*  mTheLibraryAppModel.insertShelf(shelfVO,
                    onSuccess = {
                        showToast(it)
                        finish()
                    },
                    onFailure = {
                        showToast(it)
                    })*/

                mPresenter?.createShelfData(shelfVO)

                finish()

                return@OnEditorActionListener true
            }
            false
        })
    }

    override fun navigateToBack() {
       finish()
    }

    override fun showError(errorString: String) {
        Toast.makeText(this,errorString,Toast.LENGTH_SHORT).show()
    }


}