package com.example.brownbox.matchschedule.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.brownbox.matchschedule.R
import com.example.brownbox.matchschedule.R.id.*
import com.example.brownbox.matchschedule.view.main.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @Rule
    @JvmField var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAppBehaviour(){
//        1. Memastikan RecyclerView dengan id rv_fragment ditampilkan
        onView(allOf(withId(R.id.rv_fragment), isDisplayed()))
            .check(matches(isDisplayed()))
        Thread.sleep(3000)

//        2. Melakukan Scroll pada RecyclerView sampai posisi ke 5
        onView(allOf(withId(R.id.rv_fragment), isDisplayed()))
            .perform(RecyclerViewActions
            .scrollToPosition<RecyclerView.ViewHolder>(5))
        Thread.sleep(3000)

//        3. Mengklik item pada posisi ke 4
        onView(allOf(withId(R.id.rv_fragment), isDisplayed()))
            .perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
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

//        8. Swipe ViewPager dengan id viewpager_main ke kiri
        onView(allOf(withId(R.id.viewpager_main), isDisplayed()))
            .perform(swipeLeft())

//        9. Memastikan Bottom Navigation
        onView(allOf(withId(R.id.bottom_navigation))).check(matches(isDisplayed()))

//        10. Mengklik Bottom Navigation dengan Id Item teams
        onView(allOf(withId(R.id.teams))).perform(click())
        Thread.sleep(3000)

//        11. Scroll pada RecyclerView sampai posisi ke 3
        onView(allOf(withId(R.id.teams_rv), isDisplayed()))
            .perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(3))

//        12. Mengklik item pada posisi ke 0
        onView(allOf(withId(R.id.teams_rv), isDisplayed()))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)

//        13. Swipe ViewPager dengan id detail_viewpager ke kiri
        onView(allOf(withId(R.id.detail_viewpager), isDisplayed()))
            .perform(swipeLeft())
        Thread.sleep(3000)


//        14. Mengklik Player pada Recyclerview posisi ke 2
        onView(allOf(withId(R.id.detail_players_rv), isDisplayed()))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        Thread.sleep(3000)

        pressBack()

//        15. Memastikan tombol favorite dengan id add_to_favorite ditampilkan
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))

//        16. Mengklik tombol favorite
        onView(withId(add_to_favorite)).perform(click())

//        17. Memastikan snackbar tampil dengan text "Added to favorites"
        onView(withText("Added to favorites")).check(matches(isDisplayed()))
        Thread.sleep(2000)

//        18. Mengklik tombol back
        pressBack()
        Thread.sleep(3000)

//        19. Memastikan Tampil Bottom Navigation
        onView(allOf(withId(R.id.bottom_navigation))).check(matches(isDisplayed()))

//        20. Mengklik Bottom Navigation dengan Id Favorites
        onView(allOf(withId(R.id.favorites))).perform(click())
        Thread.sleep(3000)

//        21. Mengklik posisi 1 pada Favorite Match RecyclerView
        onView(allOf(withId(R.id.rv_fav_fragment), isDisplayed()))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)

//        22. Memastikan tombol favorite dengan id add_to_favorite ditampilkan
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))

//        23. Mengklik tombol favorite untuk Remove Favorite
        onView(withId(add_to_favorite)).perform(click())

//        24. Memastikan snackbar tampil dengan text "Removed to favorites"
        onView(withText("Removed to favorites")).check(matches(isDisplayed()))
        Thread.sleep(2000)

        pressBack()

//        25. Swipe ViewPager dengan id viewpager_fav_main ke kiri
        onView(allOf(withId(R.id.viewpager_fav_main), isDisplayed()))
            .perform(swipeLeft())

//        26. Mengklik posisi 1 pada Favorite Team RecyclerView
        onView(allOf(withId(R.id.team_fav_rv), isDisplayed()))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)

//        27. Memastikan tombol favorite dengan id add_to_favorite ditampilkan
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))

//        28. Mengklik tombol favorite untuk Remove Favorite
        onView(withId(add_to_favorite)).perform(click())

//        29. Memastikan snackbar tampil dengan text "Removed to favorites"
        onView(withText("Removed to favorites")).check(matches(isDisplayed()))
        Thread.sleep(2000)

//        30. Tekan Kembali
        pressBack()


    }

}