package com.padcmyanmar.ttm.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.RatingReviewListAdapter
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.BookDetailPresenter
import com.padcmyanmar.ttm.thelibraryapp.mvp.presenters.BookDetailPresenterImpl
import com.padcmyanmar.ttm.thelibraryapp.mvp.views.BookDetailView
import kotlinx.android.synthetic.main.activity_books_and_audio_detail_view.*
import kotlinx.android.synthetic.main.activity_books_and_audio_detail_view.tvBookTitle

class BooksAndAudioDetailViewActivity : AppCompatActivity(),BookDetailView {


    //mPresenter
    private lateinit var mPresent:BookDetailPresenter



    lateinit var mRatingReviewListAdapter: RatingReviewListAdapter


    private var mBooksListVO:BooksListVO?=null
    companion object {
        private const val BOOKS_LIST_ID = "BOOKS_LIST_ID"
       // private const val CATEGORY_NAME_ID = "CATEGORY_NAME_ID"
        fun newIntent(context: Context,booksListVO: BooksListVO): Intent {
            val intent = Intent(context, BooksAndAudioDetailViewActivity::class.java)
          //  intent.putExtra(CATEGORY_NAME_ID,categoryName)
            intent.putExtra(BOOKS_LIST_ID, booksListVO)

            return intent
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_and_audio_detail_view)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setUpPresenter()
        getIntentParam()
        setUpParamDataAtUI()
        setupRatingReviewListAdapter()
        clickListener()

        mPresent.initView(this)
    }

    private fun setUpPresenter() {
        mPresent = ViewModelProvider(this)[BookDetailPresenterImpl::class.java]
        mPresent.initView(this)
    }

    private fun setUpParamDataAtUI() {
      // ivBookCover
        mBooksListVO?.bookImageHeight?.let { h->
            mBooksListVO?.bookImageWidth?.let { w ->
                Glide.with(this)
                    .load(mBooksListVO?.bookImage)
                    .placeholder(R.drawable.empty_book_icon)
                    .override(w, h)
                    .into(ivBookCover)
            }
        }

        tvAuthor.text = mBooksListVO?.author
        tvBookTitle.text = mBooksListVO?.title
        tvAboutBook.text = mBooksListVO?.description
    }

    private fun getIntentParam() {
        val intent = intent
       mBooksListVO = intent.getSerializableExtra(BOOKS_LIST_ID) as BooksListVO?
    }

    private fun clickListener() {
        rlRatingAndReview.setOnClickListener {
           mPresent.callRatingAndReviewPage()
        }

        ivAboutBook.setOnClickListener {
            mPresent.callAboutPage(mBooksListVO?.description)
        }

        btnBackBookDetail.setOnClickListener {
           mPresent.callBack()
        }
    }

    private fun setupRatingReviewListAdapter() {
        mRatingReviewListAdapter = RatingReviewListAdapter()
        rvRatingReviewList.adapter = mRatingReviewListAdapter
        rvRatingReviewList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false)

        rvRatingReviewList.isNestedScrollingEnabled = false
    }

    override fun callRatingAndReviewPage() {
        startActivity(Intent(this,RatingsAndReviewsListActivity::class.java))

    }

    override fun callAboutPage(sDesc: String?) {
        startActivity(AboutBookActivity.newIntent(this,sDesc ?: ""))
    }

    override fun callBack() {
        finish()

    }

    override fun showError(errorString: String) {
      Toast.makeText(this,errorString,Toast.LENGTH_SHORT).show()
    }


}