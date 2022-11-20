package com.padcmyanmar.ttm.thelibraryapp.uitests.firstUserFlow

import android.view.View
import android.widget.Checkable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.google.android.material.tabs.TabLayout
import com.padcmyanmar.ttm.thelibraryapp.data.vos.BooksListVO
import org.hamcrest.*
import org.hamcrest.CoreMatchers.isA
import org.hamcrest.core.AllOf.allOf


fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View?>): Matcher<View?>? {
        checkNotNull(itemMatcher)
        return object : BoundedMatcher<View?, RecyclerView?>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }
            override fun matchesSafely(item: RecyclerView?): Boolean {

                val viewHolder: RecyclerView.ViewHolder =
                    item?.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

fun <T> first(matcher: Matcher<T>): Matcher<T> {
    return object : BaseMatcher<T>(){
        var isFirst: Boolean = true
        override fun describeTo(description: Description?) {
            description?.appendText(FIRST_ITEM_DESCRIPTION)

        }

        override fun matches(item: Any?): Boolean {
            if(isFirst && matcher.matches(item))
            {
                isFirst = false
                return true
            }
            return false
        }
    }
}

fun <T> nextItemView(matcher: Matcher<T>, itemCount : Int): Matcher<T> {
    return object : BaseMatcher<T>(){
        var isFirst: Boolean = true
        var count =1
        override fun describeTo(description: Description?) {
            description?.appendText(FIRST_ITEM_DESCRIPTION)

        }

        override fun matches(item: Any?): Boolean {
            if(isFirst && matcher.matches(item))
            {
                if(count == itemCount){
                    isFirst = false
                    return true
                }else count++

            }
            return false
        }
    }
}
const val FIRST_ITEM_DESCRIPTION = "Return The First Matching Item"
val firstBookVO = BooksListVO(
    id = 1,
    categoryId = 704,
    categoryName = "Combined Print and E-Book Fiction",
    author = "Colleen Hoover",
    bookImage = "https://storage.googleapis.com/du-prd/books/images/9781668001226.jpg",
    bookImageWidth = 322,
    bookImageHeight = 500,
    bookReviewLink = "",
    contributor = "by Colleen Hoover",
    contributorNote = "",
    createdDate = "2022-11-09 23:15:26",
    description = "In the sequel to “It Ends With Us,” Lily deals with her jealous ex-husband as she reconnects with her first boyfriend.",
    price = "0.0",
    bookUri = "nyt://book/3aa85e47-4df9-53ef-9957-a77753d3502c",
    publisher = "Atria",
    title = "IT STARTS WITH US",
    updatedDate = "2022-11-09 23:19:48"

)
val secondBookVO = BooksListVO(
    id = 2,
    categoryId = 708,
    categoryName = "Combined Print and E-Book Nonfiction",
    author = "Matthew Perry",
    bookImage = "https://storage.googleapis.com/du-prd/books/images/9781250866448.jpg",
    bookImageWidth = 329,
    bookImageHeight = 500,
    bookReviewLink = "",
    contributor = "by Matthew Perry",
    contributorNote = "",
    createdDate = "2022-11-09 23:15:27",
    description = "The actor, known for playing Chandler Bing on “Friends,” shares stories from his childhood and his struggles with sobriety.",
    price = "0.0",
    bookUri = "nyt://book/04988f91-5b03-5eb4-ae17-89fda0e7051a",
    publisher = "Flatiron",
    title = "FRIENDS, LOVERS, AND THE BIG TERRIBLE THING",
    updatedDate = "2022-11-09 23:19:49"

)

val thirdBookVO = BooksListVO(
    id = 3,
    categoryId = 1,
    categoryName = "Hardcover Fiction",
    author = "John Grisham",
    bookImage = "https://storage.googleapis.com/du-prd/books/images/9780385548922.jpg",
    bookImageWidth = 329,
    bookImageHeight = 500,
    bookReviewLink = "",
    contributor = "by John Grisham",
    contributorNote = "",
    createdDate = "2022-11-09 23:15:20",
    description = "Two childhood friends follow in their fathers’ footsteps, which puts them on opposite sides of the law.",
    price = "0.0",
    bookUri = "nyt://book/b59f42c0-d9d0-5535-bf4b-21e5d04d3a46",
    publisher = "Doubleday",
    title = "THE BOYS FROM BILOXI",
    updatedDate = "2022-11-09 23:19:43"

)

fun withViewAtPosition(
    position: Int,
    itemMatcher: Matcher<View?>
): Matcher<View?>? {
    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description) {
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(recyclerView: RecyclerView): Boolean {
            val viewHolder =
                recyclerView.findViewHolderForAdapterPosition(position)
            return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
        }
    }


}
fun withDrawable(@DrawableRes id: Int) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("ImageView with drawable same as drawable with id $id")
    }

    override fun matchesSafely(view: View): Boolean {
        val context = view.context
        val expectedBitmap = context.getDrawable(id)?.toBitmap()

        return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
    }

}

fun selectTabAtPosition(tabIndex: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription() = "with tab at index $tabIndex"

        override fun getConstraints() = allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

        override fun perform(uiController: UiController, view: View) {
            val tabLayout = view as TabLayout
            val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                ?: throw PerformException.Builder()
                    .withCause(Throwable("No tab at index $tabIndex"))
                    .build()

            tabAtIndex.select()
        }
    }
}

fun clickChildViewWithId(id: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription() = "Click on a child view with specified id."

        override fun getConstraints() = null
        override fun perform(uiController: UiController?, view: View) {
            val v: View = view.findViewById(id)
            v.performClick()
        }
    }
}

fun setChecked(checked: Boolean): ViewAction? {
    return object : ViewAction {
        override fun getConstraints(): BaseMatcher<View?> {
            return object : BaseMatcher<View?>() {
                override fun matches(item: Any): Boolean {
                    return isA(Checkable::class.java).matches(item)
                }

                override fun describeMismatch(item: Any?, mismatchDescription: Description?) {}
                override fun describeTo(description: Description?) {}
            }
        }

        override fun getDescription() = null

        override fun perform(uiController: UiController, view: View) {
            val checkableView = view as Checkable
            checkableView.isChecked = checked
        }
    }
}

