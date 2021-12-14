package com.example.iqtester

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
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
        val layoutArray: ArrayList<LinearLayout> = arrayListOf(findViewById(R.id.flyingBlocks), findViewById(R.id.flyingBlocksSecond))

        for(x in layoutArray) {
            for(i in 0..13) {
                val combinedAnimations = AnimationSet(true)
                combinedAnimations.repeatCount = Animation.INFINITE

                val rotationAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.rotation)
                rotationAnimation.duration = 3000L + SecureRandom().nextInt(2000)

                val floatingAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.floating)
                floatingAnimation.duration = 20000L + SecureRandom().nextInt(10000)

                val bigRotationAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.big_rotation)
                bigRotationAnimation.duration = 2500L + SecureRandom().nextInt(7500)
                bigRotationAnimation.startOffset = SecureRandom().nextInt(5000).toLong()

                combinedAnimations.addAnimation(rotationAnimation)
                combinedAnimations.addAnimation(floatingAnimation)
                combinedAnimations.addAnimation(bigRotationAnimation)

                combinedAnimations.startOffset = (layoutArray.indexOf(x) * floatingAnimation.duration.toInt() / 2) + SecureRandom().nextInt(floatingAnimation.duration.toInt() / 2).toLong()

                val img = ImageView(this)
                img.layoutParams = LinearLayout.LayoutParams(64, 64).apply { gravity = Gravity.BOTTOM }
                img.setImageResource(R.drawable.heart)
                img.x = SecureRandom().nextInt(100).toFloat()

                img.startAnimation(combinedAnimations)

                x.addView(img)
            }
        }
    }
}