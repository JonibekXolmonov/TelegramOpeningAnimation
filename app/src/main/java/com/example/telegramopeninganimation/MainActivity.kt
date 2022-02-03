package com.example.telegramopeninganimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.tabs.TabLayout
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var pages: List<Page>
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var mainTab: TabLayout
    private lateinit var lottieAnimationView: LottieAnimationView
    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        lottieAnimationView = findViewById(R.id.lottie_animation)

        viewPager = findViewById(R.id.vp_main)
        mainTab = findViewById(R.id.tab_main)
        pages = addPages()

        pagerAdapter = PagerAdapter(pages, this)

        viewPager.adapter = pagerAdapter
        mainTab.setupWithViewPager(viewPager)

        viewPager.addAutoScrollToViewPager()

        viewPager.controlAnimation()
    }

    private fun ViewPager.controlAnimation() {

        this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                when (position) {
                    0 -> {
                        lottieAnimationView.setAnimation("telegram_opening.json")
                    }
                    1 -> {
                        lottieAnimationView.setAnimation("telegram_fast.json")
                    }
                    2 -> {
                        lottieAnimationView.setAnimation("telegram_free.json")
                    }
                    3 -> {
                        lottieAnimationView.setAnimation("telegram_powerful.json")
                    }
                    4 -> {
                        lottieAnimationView.setAnimation("telegram_secure.json")
                    }
                    else -> {
                        lottieAnimationView.setAnimation("telegram_cloud.json")
                    }
                }
                lottieAnimationView.playAnimation()
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

    private fun ViewPager.addAutoScrollToViewPager() {
        val DELAY_MS: Long = 1000 //delay in milliseconds before task is to be executed

        val PERIOD_MS: Long = 3000 // time in milliseconds between successive task executions.

        val handler = Handler()
        val update = Runnable {
            if (this.currentItem == pagerAdapter.count - 1) {
                this.currentItem = 0
            } else {
                this.setCurrentItem(this.currentItem + 1, true)
            }
        }

        timer = Timer()

        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, DELAY_MS, PERIOD_MS)
    }

    private fun addPages(): ArrayList<Page> {
        return ArrayList<Page>().apply {
            this.add(Page("Telegram X", "The world's fastest messaging app. It is free and secure."))
            this.add(Page("Fast", "Telegram delivers messages faster than any other application."))
            this.add(Page("Free", "Telegram is free forever. No ads. No subscription  fees."))
            this.add(Page("Powerful", "Telegram has no limits on the size  of your media and chats."))
            this.add(Page("Secure", "Telegram keeps your messages safe from hacker attacks."))
            this.add(Page("Cloud-based", "Telegram lets you access messages from multiple devices."))
        }
    }
}