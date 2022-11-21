package com.padcmyanmar.ttm.thelibraryapp.uitests.secondUserFlow

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.google.android.material.chip.Chip
import com.padcmyanmar.ttm.thelibraryapp.R
import com.padcmyanmar.ttm.thelibraryapp.activities.MainActivity
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import com.padcmyanmar.ttm.thelibraryapp.uitests.firstUserFlow.*
import com.padcmyanmar.ttm.thelibraryapp.viewholders.UnReadBooksViewHolder
import com.padcmyanmar.ttm.thelibraryapp.viewholders.ViewTypeListBookListViewHolder
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf
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

        //
        onView(withText("Your books"))
            .perform(click())

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

        Thread.sleep(1000)
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

        Thread.sleep(1000)
        onView(withText("Your shelves"))
            .perform(click())
        Thread.sleep(1000)
        onView(withText("1 Book"))
            .check(matches(isDisplayed()))



        // second book add to shelf
        onView(withText("Your books"))
            .perform(click())
        onView((withId(R.id.rvBookListUI))).perform(
            RecyclerViewActions.actionOnItemAtPosition<ViewTypeListBookListViewHolder>(
                1, clickChildViewWithId(
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

        Thread.sleep(1000)
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
        Thread.sleep(1000)
        onView(withText("Your shelves"))
            .perform(click())
        Thread.sleep(1000)
        onView(withText("2 Books"))
            .check(matches(isDisplayed()))



//third book add to shelf

        onView(withText("Your books"))
            .perform(click())
        onView((withId(R.id.rvBookListUI))).perform(
            RecyclerViewActions.actionOnItemAtPosition<ViewTypeListBookListViewHolder>(
                2, clickChildViewWithId(
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

        Thread.sleep(1000)
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
        Thread.sleep(1000)
        onView(withText("Your shelves"))
            .perform(click())
        Thread.sleep(1000)
        onView(withText("3 Books"))
            .check(matches(isDisplayed()))



        onView(withId(R.id.ivGoToShelvesList))
            .perform(click())
        onView(withText(TEST_SHELF_NAME))
            .check(matches(isDisplayed()))
        onView(withText("3 Books"))
            .check(matches(isDisplayed()))


        //check chip name
        chipContainsText(firstBookVO.categoryName.toString())
        chipContainsText(secondBookVO.categoryName.toString())
        chipContainsText(thirdBookVO.categoryName.toString())

        //click event for second and third category chip click
        chipCLickAction(firstBookVO)
        chipCLickAction(secondBookVO)
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


        //for rename shelf
        onView(withId(R.id.btnContextualMenu))
            .perform(click())
        onView(withText(TEST_SHELF_NAME))
            .check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withText(R.id.llRenameShelf))
            .check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withText(R.id.llDeleteShelf))
            .check(matches(isDisplayed()))
        onView(withText(R.id.llRenameShelf))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.etShelvesName))
            .check(matches(isDisplayed()))
        onView(withText(TEST_SHELF_NAME))
            .check(matches(isDisplayed()))
        onView(withId(R.id.etShelvesName)).perform(
            ViewActions.typeText(TEST_NEW_SHELF_NAME),
            ViewActions.pressImeActionButton()
        )
        Thread.sleep(1000)
        onView(withText(TEST_NEW_SHELF_NAME))
            .check(matches(isDisplayed()))



        //for delete shelf
        onView(withId(R.id.btnContextualMenu))
            .perform(click())
        onView(withText(R.id.llDeleteShelf))
            .perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.llEmptyView))
            .check(matches(isDisplayed()))

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
                    .check(matches(Matchers.not(isChecked())))
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
                    .check(matches(Matchers.not(isChecked())))
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
    companion object{
        const val TEST_SHELF_NAME = "10 Interaction Design Books to Read"
        const val TEST_NEW_SHELF_NAME = "5 Interaction Design Books to Read"
    }
}