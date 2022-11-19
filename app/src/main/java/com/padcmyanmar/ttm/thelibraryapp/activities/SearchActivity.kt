package com.padcmyanmar.ttm.thelibraryapp.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.jakewharton.rxbinding4.widget.textChanges
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.GooglePlayBookAdapter
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.dummy.dummyBookTypeList
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.SearchBookPresenter
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.SearchBookPresenterImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.SearchBookView
import kotlinx.android.synthetic.main.activity_search.*
import java.util.concurrent.TimeUnit


class SearchActivity : AppCompatActivity(),SearchBookView {

   //mPresenter
    private lateinit var mPresenter: SearchBookPresenter

    lateinit var mGooglePlayEBookAdapter: GooglePlayBookAdapter
    lateinit var mGooglePlayAudioBookAdapter: GooglePlayBookAdapter

    var mBookItemVOList: List<BooksListVO> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setUpPresenter()
        //book type
        setUpBooksTypeTabLayout()
        setUpPlayBookAdapter()

        clickListener()
        mPresenter.initView(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[SearchBookPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun clickListener() {
        etSearchBooks.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap { mPresenter.searchGoogleBooksList(it.toString()) }
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
            .observeOn(io.reactivex.rxjava3.android.schedulers.AndroidSchedulers.mainThread())
            .subscribe({
               // rvEbookGooglePlayBook.setNewData(it)
                mBookItemVOList = it

                it?.let {
                    if(it.isNotEmpty())
                    {
                        llTab.visibility = View.VISIBLE
                        rvEbookGooglePlayBook.visibility = View.VISIBLE
                        rvAudiobookGooglePlayBook.visibility = View.GONE
                        mGooglePlayEBookAdapter.setData(it)
                    }
                }

            },
                {
                    showError(it.localizedMessage ?: "")
                })

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
        mGooglePlayEBookAdapter = GooglePlayBookAdapter(mPresenter)
        rvEbookGooglePlayBook.adapter = mGooglePlayEBookAdapter
        rvEbookGooglePlayBook.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false)

        rvEbookGooglePlayBook.isNestedScrollingEnabled = false

        mGooglePlayAudioBookAdapter = GooglePlayBookAdapter(mPresenter)
        rvAudiobookGooglePlayBook.adapter = mGooglePlayAudioBookAdapter
        rvAudiobookGooglePlayBook.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false)

        rvAudiobookGooglePlayBook.isNestedScrollingEnabled = false
    }

    override fun navigateToBookDetailPage(mBooksListVO: BooksListVO?) {

        mBooksListVO?.let { mPresenter.saveReadBook(it) }

       startActivity(mBooksListVO?.let {
           BooksAndAudioDetailViewActivity.newIntent(this,
               it
           )
       })
    }

    override fun showError(errorString: String) {
        Toast.makeText(this,errorString,Toast.LENGTH_SHORT).show()
    }
}