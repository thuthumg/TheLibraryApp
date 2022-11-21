package com.padcmyanmar.ttm.thelibraryapp.uitests.thirdUserFlow

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.MainActivity
import com.padcmyanmar.ttm.thelibraryapp.uitests.firstUserFlow.first
import com.padcmyanmar.ttm.thelibraryapp.uitests.secondUserFlow.ShelvesTest
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SearchTest {

    private val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun testCase1ForSearchFunction(){

        onView(withId(R.id.toolBarSearch))
            .perform(click())
        onView(withId(R.id.etSearchBooks))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.etSearchBooks)).perform(
            ViewActions.typeText(SEARCH_QUERY),
            ViewActions.pressImeActionButton()
        )
        onView(withId(R.id.rvEbookGooglePlayBook))
            .check(matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)
        onView((withText("Android For Dummies")))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    companion object{
        const val SEARCH_QUERY = "android"
    }
}