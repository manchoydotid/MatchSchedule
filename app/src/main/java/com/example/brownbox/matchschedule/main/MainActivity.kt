package com.example.brownbox.matchschedule.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.brownbox.matchschedule.PagerAdapter
import com.example.brownbox.matchschedule.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager_main.adapter = PagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)

        supportActionBar?.elevation = 0F
    }
}
