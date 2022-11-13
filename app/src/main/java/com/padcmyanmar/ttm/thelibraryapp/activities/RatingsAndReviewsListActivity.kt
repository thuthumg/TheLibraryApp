package com.padcmyanmar.ttm.thelibraryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.adapters.FilterRatingAdapter
import com.padcmyanmar.ttm.thelibraryapp.adapters.RatingReviewListAdapter
import kotlinx.android.synthetic.main.activity_books_and_audio_detail_view.*
import kotlinx.android.synthetic.main.activity_books_and_audio_detail_view.rvRatingReviewList
import kotlinx.android.synthetic.main.activity_ratings_and_reviews_list.*

class RatingsAndReviewsListActivity : AppCompatActivity() {

    private lateinit var mRatingReviewListAdapter: RatingReviewListAdapter
    lateinit var mFilterRatingListAdapter: FilterRatingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ratings_and_reviews_list)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setupRatingReviewListAdapter()
        setUpFilterRatingAdapter()
        clickListener()
    }

    private fun clickListener() {
        btnBackFilterRating.setOnClickListener {
            finish()
        }
    }

    private fun setUpFilterRatingAdapter() {
        mFilterRatingListAdapter = FilterRatingAdapter()
        rvFilterRating.adapter = mFilterRatingListAdapter
        rvFilterRating.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false)

        rvFilterRating.isNestedScrollingEnabled = false
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