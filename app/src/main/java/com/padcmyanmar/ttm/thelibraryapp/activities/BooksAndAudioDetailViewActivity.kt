package com.padcmyanmar.ttm.thelibraryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.RatingReviewListAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.ShelvesListAdapter
import kotlinx.android.synthetic.main.activity_books_and_audio_detail_view.*
import kotlinx.android.synthetic.main.fragment_your_shelves.*
import kotlin.random.Random

class BooksAndAudioDetailViewActivity : AppCompatActivity() {

    lateinit var mRatingReviewListAdapter: RatingReviewListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_and_audio_detail_view)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setupRatingReviewListAdapter()
        clickListener()
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