package com.example.batterymanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    private lateinit var subTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        subTitle = findViewById<TextView>(R.id.tv_subtitle)

        var textArray = arrayListOf(
            "Make your battery powerful",
            "Make your battery safe",
            "Make your battery faster",
            "Make your battery strong",
            "Manage your Phone battery manage",
            "Notify when your phone full charge"
        )

        var i = 1
        for (i in 1..6) {
            subTitleGenerator((i * 1000).toLong(), textArray[i - 1])
        }

        Timer().schedule(timerTask {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish() // Close Splash Activity
        }, 7000)
    }

    private fun subTitleGenerator(delayTime: Long, subtitle: String) {
        Timer().schedule(timerTask {
            runOnUiThread(timerTask {
                subTitle.text = subtitle
            })
        }, delayTime)
    }

}