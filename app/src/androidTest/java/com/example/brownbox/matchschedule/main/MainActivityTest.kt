package com.example.brownbox.matchschedule.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.R.id.*
import org.hamcrest.CoreMatchers.allOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour(){
//        1. Memastikan RecyclerView dengan id rv_fragment ditampilkan
        onView(allOf(withId(R.id.rv_fragment), isDisplayed()))
            .check(matches(isDisplayed()))
        Thread.sleep(3000)

//        2. Melakukan Scroll pada RecyclerView sampai posisi ke 9
        onView(allOf(withId(R.id.rv_fragment), isDisplayed()))
            .perform(RecyclerViewActions
            .scrollToPosition<RecyclerView.ViewHolder>(9))

//        3. Mengklik item pada posisi ke 7
        onView(allOf(withId(R.id.rv_fragment), isDisplayed()))
            .perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))
        Thread.sleep(3000)

//        4. Memastikan tombol favorite dengan id add_to_favorite ditampilkan
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))

//        5. Mengklik tombol favorite
        onView(withId(add_to_favorite)).perform(click())

//        6. Memastikan snackbar tampil dengan text "Added to favorites"
        onView(withText("Added to favorites")).check(matches(isDisplayed()))
        Thread.sleep(2000)

//        7. Mengklik tombol back
        pressBack()
        Thread.sleep(3000)

//        8. Swipe ViewPager dengan id viewpager_main 2 kali
        onView(allOf(withId(R.id.viewpager_main), isDisplayed()))
            .perform(swipeLeft())
        onView(allOf(withId(R.id.viewpager_main), isDisplayed()))
            .perform(swipeLeft())

//        9. Memastikan RecyclerView dengan id rv_fav_fragment ditampilkan
        onView(allOf(withId(R.id.rv_fav_fragment), isDisplayed()))
            .check(matches(isDisplayed()))
        Thread.sleep(3000)

//        10. Mengklik item pada posisi ke 1
        onView(allOf(withId(R.id.rv_fav_fragment), isDisplayed()))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)

//        11. Memastikan tombol favorite dengan id add_to_favorite ditampilkan
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))

//        12. Mengklik tombol favorite
        onView(withId(add_to_favorite)).perform(click())

//        13. Memastikan snackbar tampil dengan text "Remove to favorites"
        onView(withText("Removed to favorites")).check(matches(isDisplayed()))
        Thread.sleep(2000)

//        14. Mengklik tombol back
        pressBack()
    }

}