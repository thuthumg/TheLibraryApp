package com.padcmyanmar.ttm.thelibraryapp.uitests.firstUserFlow

import android.content.Intent

import android.view.View
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions

import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.google.android.material.chip.Chip
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.MainActivity
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.viewholders.UnReadBooksViewHolder

import kotlinx.android.synthetic.main.fragment_home.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.hamcrest.Matchers.not
import org.hamcrest.core.AllOf

import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import java.util.*


@RunWith(AndroidJUnit4ClassRunner::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomePageTest {

    private val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }


    @Test
    fun testCase1ForCarouselViewEmptyOrNot() {

        //for empty
        onView(withId(R.id.carouselView))
            .check(matches(hasChildCount(0)))

        //for count 3
//        onView(withId(R.id.carouselView))
//            .check(matches(hasChildCount(1)))
//
//        onView(withId(R.id.carouselView))
//            .check(matches(hasChildCount(2)))
//
//        onView(withId(R.id.carouselView))
//            .check(matches(hasChildCount(3)))

    }

    @Test
    @Throws(InterruptedException::class)
    fun testCase2ForCategoryRecyclerViewVisibility() {
        Thread.sleep(3000)
        onView(ViewMatchers.withId(R.id.rvEBooksAndAudioBooksList))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(
                        activityTestRule.activity.window.decorView
                    )
                )
            )
            .check(matches(isDisplayed()))

        //check category name
        onView(withText("Combined Print & E-Book Fiction"))
            .check(matches(isDisplayed()))

        //check 3 book title
        onView(first<View>(withText("IT STARTS WITH US")))
            .check(matches(isDisplayed()))
        onView(first<View>(withText("GOING ROGUE")))
            .check(matches(isDisplayed()))
        onView(first<View>(withText("IT ENDS WITH US")))
            .check(matches(isDisplayed()))


    }

    @Test
    @Throws(InterruptedException::class)
    fun testCase3ForCategoryRecyclerItemClick() {

        Thread.sleep(3000)
        onView(first<View>(withId(R.id.rvUnReadBooksItemList))).perform(
            RecyclerViewActions.actionOnItemAtPosition<UnReadBooksViewHolder>(0, click())
        )

        testCaseForDetailPage(firstBookVO)

        //go to position 2
        Thread.sleep(3000)
        onView((withId(R.id.rvEBooksAndAudioBooksList))).perform(
            RecyclerViewActions.actionOnItemAtPosition<UnReadBooksViewHolder>(2, scrollTo())
        )
        //click item from position 2
        onView(nextItemView<View>(withId(R.id.rvUnReadBooksItemList), 2)).perform(
            RecyclerViewActions.actionOnItemAtPosition<UnReadBooksViewHolder>(0, click())
        )

        testCaseForDetailPage(secondBookVO)

        Thread.sleep(3000)
        onView((withId(R.id.rvEBooksAndAudioBooksList))).perform(
            RecyclerViewActions.actionOnItemAtPosition<UnReadBooksViewHolder>(3, scrollTo())
        )
        //click item from position 3
        onView(nextItemView<View>(withId(R.id.rvUnReadBooksItemList), 3)).perform(
            RecyclerViewActions.actionOnItemAtPosition<UnReadBooksViewHolder>(0, click())
        )

        testCaseForDetailPage(thirdBookVO)


    }

    private fun testCaseForDetailPage(bookVO: BooksListVO) {
        onView(withId(R.id.ivBookCover))
            .check(matches(isDisplayed()))
        // .check(matches(withDrawable(R.drawable.test_image_2)))


        onView(withId(R.id.tvBookTitle))
            .check(matches(isDisplayed()))
        onView(first<View>(withText(bookVO.title)))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvAuthor))
            .check(matches(isDisplayed()))
        onView((withText(bookVO.title)))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvBookTypeAndPages))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvAboutBook))
            .check(matches(isDisplayed()))
        onView((withText(bookVO.description)))
            .check(matches(isDisplayed()))

        onView(withId(R.id.rlRatingAndReview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.lblAboutThisEbook))
            .check(matches(isDisplayed()))
        onView(withId(R.id.mBtnFreeSample))
            .check(matches(isDisplayed()))
        onView(withId(R.id.mBtnAddToWishlist))
            .check(matches(isDisplayed()))
        //onView(withId(R.id.rvRatingReviewList)).perform(scrollTo())
        //.check(matches(isDisplayed()))
        //onView((withId(R.id.lblPublish)) ).perform(scrollTo())
        //.check(matches(isDisplayed()))

        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun testCase5ForLibraryCase(){
        //go to library
        onView(withId(R.id.action_library))
            .perform(click())
            .check(ViewAssertions.matches(isDisplayed()))
        //check chip name
        chipContainsText(firstBookVO.categoryName.toString())
        chipContainsText(secondBookVO.categoryName.toString())
        chipContainsText(thirdBookVO.categoryName.toString())


        //click event for first category chip
        chipCLickAction(firstBookVO)
        //click event for second category chip
         chipCLickAction(secondBookVO)
        //click event for third category chip click
        chipCLickAction(thirdBookVO)


        //check sort function
        //click action for title
        sortClickAction("title")
        //click action for author
        sortClickAction("author")
        //click action for recently opened
        sortClickAction("recent")

        //change grid UI function
        //click action for large grid UI
        viewAsClickAction("large")
        //click action for small grid UI
        viewAsClickAction("small")
        //click action for list UI
        viewAsClickAction("list")

    }

    private fun chipContainsText(text: String) {
        onView(
            AllOf.allOf(
                withText(CoreMatchers.containsString(text)),
                isAssignableFrom(Chip::class.java)
            )
        ).perform(scrollTo()).check(ViewAssertions.matches(isDisplayed()))
    }

    private fun chipCLickAction(bookVO: BooksListVO){

        if(!(bookVO.categoryName.equals(firstBookVO.categoryName)))
        {
            Thread.sleep(3000)
            onView(
                AllOf.allOf(
                    withText(CoreMatchers.containsString(bookVO.categoryName)),
                    isAssignableFrom(Chip::class.java)
                )
            ).perform(scrollTo())
        }


        onView(
            AllOf.allOf(
                withText(CoreMatchers.containsString(bookVO.categoryName)),
                isAssignableFrom(Chip::class.java)
            )
        ).perform(click())


        Thread.sleep(3000)
        onView(ViewMatchers.withId(R.id.rvBookListUI))
            .check(matches(isDisplayed()))

        onView(first<View>(withText(bookVO.title)))
            .check(matches(isDisplayed()))

        onView(withId(R.id.ivClearFilter))
            .perform(click())

        onView(first<View>(withText(firstBookVO.title)))
            .check(matches(isDisplayed()))
        onView(first<View>(withText(secondBookVO.title)))
            .check(matches(isDisplayed()))
        onView(first<View>(withText(thirdBookVO.title)))
            .check(matches(isDisplayed()))
    }

    private fun sortClickAction(s: String) {
        onView(withId(R.id.ivChangeSortType))
            .perform(click())
        onView(withId(R.id.rbSortByGroup))
            .perform(click())

       when(s){
           "title"->{
               onView((withId(R.id.rbRecentlyOpened)))
                   .check(matches(isChecked()))
               onView((withId(R.id.rbTitle)))
                   .check(matches(not(isChecked())))
               onView((withId(R.id.rbAuthor)))
                   .check(matches(isNotChecked()))
               onView((withId(R.id.rbTitle)))
                   .perform(click())
               Thread.sleep(1000)
               onView((withId(R.id.rvBookListUI)))
                   .check(matches(isDisplayed()))
               onView(first<View>(withText(secondBookVO.title)))
                   .check(matches(isDisplayed()))
           }
           "author"->{
               onView((withId(R.id.rbRecentlyOpened)))
                   .check(matches(isNotChecked()))
               onView((withId(R.id.rbTitle)))
                   .check(matches(isChecked()))
               onView((withId(R.id.rbAuthor)))
                   .check(matches(isNotChecked()))
               onView((withId(R.id.rbAuthor)))
                   .perform(click())
               Thread.sleep(1000)
               onView((withId(R.id.rvBookListUI)))
                   .check(matches(isDisplayed()))
               onView(first<View>(withText(firstBookVO.title)))
                   .check(matches(isDisplayed()))
           }
           else-> {
               onView((withId(R.id.rbRecentlyOpened)))
                   .check(matches(isNotChecked()))
               onView((withId(R.id.rbTitle)))
                   .check(matches(isNotChecked()))
               onView((withId(R.id.rbAuthor)))
                   .check(matches(isChecked()))
               onView((withId(R.id.rbRecentlyOpened)))
                   .perform(click())
               Thread.sleep(1000)
               onView((withId(R.id.rvBookListUI)))
                   .check(matches(isDisplayed()))
               onView(first<View>(withText(thirdBookVO.title)))
                   .check(matches(isDisplayed()))
           }
       }




    }

    private fun viewAsClickAction(s: String) {
        onView(withId(R.id.ivChangeListView))
            .perform(click())
        onView(withId(R.id.rbViewGroup))
            .perform(click())

        when(s){
            "large"->{
                onView((withId(R.id.rbList)))
                    .check(matches(isChecked()))
                onView((withId(R.id.rbLargeGrid)))
                    .check(matches(not(isChecked())))
                onView((withId(R.id.rbSmallGrid)))
                    .check(matches(isNotChecked()))
                onView((withId(R.id.rbLargeGrid)))
                    .perform(click())
                Thread.sleep(1000)
                onView((withId(R.id.rvBookListLargeUI)))
                    .check(matches(isDisplayed()))
            }
            "small"->{
                onView((withId(R.id.rbList)))
                    .check(matches(isNotChecked()))
                onView((withId(R.id.rbLargeGrid)))
                    .check(matches(isChecked()))
                onView((withId(R.id.rbSmallGrid)))
                    .check(matches(isNotChecked()))
                onView((withId(R.id.rbSmallGrid)))
                    .perform(click())
                Thread.sleep(1000)
                onView((withId(R.id.rvBookListSmallUI)))
                    .check(matches(isDisplayed()))

            }
            else-> {
                onView((withId(R.id.rbList)))
                    .check(matches(isNotChecked()))
                onView((withId(R.id.rbLargeGrid)))
                    .check(matches(isNotChecked()))
                onView((withId(R.id.rbSmallGrid)))
                    .check(matches(isChecked()))
                onView((withId(R.id.rbList)))
                    .perform(click())
                Thread.sleep(1000)
                onView((withId(R.id.rvBookListUI)))
                    .check(matches(isDisplayed()))

            }
        }




    }

}

