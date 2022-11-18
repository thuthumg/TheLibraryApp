package com.padcmyanmar.ttm.thelibraryapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.AddToShelvesListAdapter
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.data.vos.ShelfVO

import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.AddToShelvesListPresenter
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.AddToShelvesListPresenterImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.AddToShelvesListView
import kotlinx.android.synthetic.main.activity_add_to_shelves.*


class AddToShelvesActivity : AppCompatActivity(), AddToShelvesListView {

    //mPresenter
    private lateinit var mPresenter: AddToShelvesListPresenter

    lateinit var mBooksListVO: BooksListVO

   // lateinit var mShelfVO: ShelfVO

    var mShelfVOList: List<ShelfVO> = listOf()

    var mBooksListVOList: ArrayList<BooksListVO> = arrayListOf()

    companion object {
        private const val BOOKS_LIST_VO = "BOOKS_LIST_VO"

        fun newIntent(context: Context, booksListVO: BooksListVO?): Intent {
            val intent = Intent(context, AddToShelvesActivity::class.java)
            intent.putExtra(BOOKS_LIST_VO,booksListVO)
            return intent
        }
    }


    private lateinit var mAddToShelvesListAdapter:AddToShelvesListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_shelves)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setUpPresenter()
        getIntentParam()
        setUpAddToShelvesList()
        clickListener()

        mPresenter.onUiReady(this)
    }
    private fun getIntentParam(){
        val intent = intent
        mBooksListVO = intent.getSerializableExtra(
            BOOKS_LIST_VO
        ) as BooksListVO

    }
    private fun setUpPresenter() {
       mPresenter = ViewModelProvider(this)[AddToShelvesListPresenterImpl::class.java]
       mPresenter.initView(this)
    }

    private fun clickListener() {
       ivClose.setOnClickListener {
           finish()
       }

        mbtnConfirm.setOnClickListener {
            mPresenter.saveShelfData(mShelfVOList)
            finish()


        }
    }

    private fun setUpAddToShelvesList() {
        mAddToShelvesListAdapter = AddToShelvesListAdapter(mBooksListVO,mPresenter)
        rvShelvesBooksItemList.adapter = mAddToShelvesListAdapter
        rvShelvesBooksItemList.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        rvShelvesBooksItemList.isNestedScrollingEnabled = false
    }

    override fun showShelvesListData(shelvesList: List<ShelfVO>) {

        if(shelvesList.isNotEmpty()) {
            mShelfVOList = shelvesList
            mAddToShelvesListAdapter.setData(shelvesList)
            mbtnConfirm.isEnabled = true
        }else{
            mbtnConfirm.isEnabled = false
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun addToShelvesData(shelfId: Int, selectedShelfData: Boolean) {
        mBooksListVOList = arrayListOf()
        mShelfVOList.forEach {

            Log.d("addtoshelf","check add to shelf = ${it.id} // $shelfId // $selectedShelfData")

            if(it.id == shelfId && it.isSelected)
            {

                it.booksList?.let {bookList->
                    bookList.forEach { bookListVO->

                        if(bookListVO.id != mBooksListVO.id)
                        mBooksListVOList.add(bookListVO)
                    }
                }
                mBooksListVOList.add(mBooksListVO)
                it.booksList = mBooksListVOList


            }else if(it.id == shelfId && !it.isSelected){
                it.booksList?.let {bookList->
                    bookList.forEach { bookListVO->

                        if(bookListVO.id != mBooksListVO.id)
                        mBooksListVOList.add(bookListVO)
                    }
                }

                it.booksList = mBooksListVOList

            }
        }


    }

    override fun showError(errorString: String) {
       Toast.makeText(this,errorString,Toast.LENGTH_SHORT).show()
    }


}