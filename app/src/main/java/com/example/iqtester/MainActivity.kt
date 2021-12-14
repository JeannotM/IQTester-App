package com.example.iqtester

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.core.view.children
import java.security.SecureRandom
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterUserDataButton: Button = findViewById(R.id.start_quiz)
        val lastFewUsers: ImageButton = findViewById(R.id.checkLastFewUsers)
        val aboutMePage: ImageButton = findViewById(R.id.checkAboutMe)

        lastFewUsers.setOnClickListener {
            startActivity(Intent(this, LastFewUsers::class.java))
        }

        aboutMePage.setOnClickListener {
            startActivity(Intent(this, AboutMePage::class.java))
        }

        enterUserDataButton.setOnClickListener {
            startActivity(Intent(this, EnterUserData::class.java))
        }

        startCoolBackground()
    }

    private fun startCoolBackground() {
        val layout: LinearLayout = findViewById(R.id.flyingBlocks)
        val secondLayout: LinearLayout = findViewById(R.id.flyingBlocksSecond)

        for(i in layout.children) {
            val floatingAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.floating)
            floatingAnimation.startOffset = SecureRandom().nextInt(floatingAnimation.duration.toInt() / 2).toLong()
            i.startAnimation(floatingAnimation)
            i.x = SecureRandom().nextInt(100).toFloat()
        }

        for(i in secondLayout.children) {
            val floatingAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.floating)
            floatingAnimation.startOffset = floatingAnimation.duration / 2 + SecureRandom().nextInt(floatingAnimation.duration.toInt() / 2).toLong()
            i.startAnimation(floatingAnimation)
            i.x = SecureRandom().nextInt(100).toFloat()
        }
    }
}