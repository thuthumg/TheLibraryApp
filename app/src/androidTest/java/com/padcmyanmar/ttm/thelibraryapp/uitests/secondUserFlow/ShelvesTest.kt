package com.padcmyanmar.ttm.thelibraryapp.uitests.secondUserFlow

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.MainActivity
import com.padcmyanmar.ttm.thelibraryapp.uitests.firstUserFlow.*
import com.padcmyanmar.ttm.thelibraryapp.viewholders.UnReadBooksViewHolder
import com.padcmyanmar.ttm.thelibraryapp.viewholders.ViewTypeListBookListViewHolder
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ShelvesTest {

    private val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun testCase1ForShelfEmptyOrNot_CreateShelf(){

        onView(withId(R.id.action_library))
            .perform(click())
        onView(withText("Your shelves"))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.llEmptyView))
            .check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.mBtnCreateNew))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.txtInputEditTextShelf))
            .check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.txtInputEditTextShelf)).perform(
            ViewActions.typeText(TEST_SHELF_NAME),
            ViewActions.pressImeActionButton()
        )
        Thread.sleep(1000)
        onView(withId(R.id.rvShelvesList))
            .check(matches(isDisplayed()))
        onView(first<View>(withText(TEST_SHELF_NAME)))
            .check(matches(isDisplayed()))
        Thread.sleep(1000)

    }

    @Test
    fun testCase2ForAddToShelves(){
        onView(withId(R.id.action_library))
            .perform(click())
        onView(withText("Your books"))
            .perform(click())
        //onView((withId(R.id.rvBookListUI))).check(matches(isDisplayed()))

//        onView((withId(R.id.rvBookListUI))).perform(
//            RecyclerViewActions.actionOnItemAtPosition<ViewTypeListBookListViewHolder>(0, click())
//        )

        onView((withId(R.id.rvBookListUI))).perform(
                    RecyclerViewActions.actionOnItemAtPosition<ViewTypeListBookListViewHolder>(
                        0, clickChildViewWithId(
                         R.id.ivContextualMenu))
        )

        onView(first<View>(withId(R.id.tvBookTitle)))
            .check(matches(isDisplayed()))
        onView(first<View>(withId(R.id.tvAuthor)))
            .check(matches(isDisplayed()))
        onView(first<View>(withId(R.id.llRemoveDownload)))
            .check(matches(isDisplayed()))
        onView(first<View>(withId(R.id.llDeleteFromLibrary)))
            .check(matches(isDisplayed()))
        onView(first<View>(withId(R.id.llAddToShelves)))
            .check(matches(isDisplayed()))
        onView(first<View>(withId(R.id.lblAboutThisEbook)))
            .check(matches(isDisplayed()))

        Thread.sleep(3000)
        onView(first<View>(withId(R.id.llAddToShelves)))
            .perform(click())
        onView(withId(R.id.rvShelvesBooksItemList))
            .check(matches(isDisplayed()))

        onView(withText(TEST_SHELF_NAME))
            .check(matches(isDisplayed()))

        //Thread.sleep(3000)
        onView((withId(R.id.chkBookItem))).perform(scrollTo(), setChecked(true))

        onView(withId(R.id.mbtnConfirm))
            .perform(click())

        Thread.sleep(3000)
        onView(withId(R.id.rvShelvesBooksItemList))
            .check(matches(isDisplayed()))
            .perform(click())

        // to check photo
        //?










    }

    companion object{
        const val TEST_SHELF_NAME = "10 Interaction Design Books to Read"
    }
}