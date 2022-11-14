package com.padcmyanmar.ttm.thelibraryapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.RatingReviewListAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.ShelvesListAdapter
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import kotlinx.android.synthetic.main.activity_books_and_audio_detail_view.*
import kotlinx.android.synthetic.main.activity_books_and_audio_detail_view.tvBookTitle
import kotlinx.android.synthetic.main.fragment_your_shelves.*
import kotlinx.android.synthetic.main.view_holder_unread_books_list.*
import kotlinx.android.synthetic.main.view_holder_unread_books_list.view.*
import kotlin.random.Random

class BooksAndAudioDetailViewActivity : AppCompatActivity() {

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
        getIntentParam()
        setUpParamDataAtUI()
        setupRatingReviewListAdapter()
        clickListener()
    }

    private fun setUpParamDataAtUI() {
      // ivBookCover
        mBooksListVO?.bookImageHeight?.let { h->
            mBooksListVO?.bookImageWidth?.let { w ->
                Glide.with(this)
                    .load(mBooksListVO?.bookImage)
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
            startActivity(Intent(this,RatingsAndReviewsListActivity::class.java))
        }

        ivAboutBook.setOnClickListener {
            startActivity(Intent(this,AboutBookActivity::class.java))
        }

        btnBackBookDetail.setOnClickListener {
            finish()
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


}