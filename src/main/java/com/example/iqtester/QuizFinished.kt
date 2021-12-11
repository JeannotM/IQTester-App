package com.example.iqtester

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Text
import java.security.SecureRandom
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class QuizFinished : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_finished)
        thread { countUpToIQ() }
    }

    private fun countUpToIQ () {
        val extras = intent.extras
        var name = ""
        var IQ = 0

        if(extras != null) {
            name = extras.getString("name").toString()
            IQ = extras.getInt("iq")
        }

        val congratulations: TextView = findViewById(R.id.gefeliciteerd)
        congratulations.text = ("Gefeliciteerd " + name + "!!")

        val grownIn: Animation = AnimationUtils.loadAnimation(this, R.anim.growin)
        val intelligentQuota: TextView = findViewById(R.id.iq_equals)
        intelligentQuota.startAnimation(grownIn)

        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        progressBar.max = IQ

        Thread.sleep(1500)

        val iqView: TextView = findViewById(R.id.iq_view)
        var extraTime: Long = 0;
        for (i in 0..IQ) {
            if(IQ - i < 50)
                extraTime += 1

            Thread.sleep((15L + extraTime))
            iqView.text = i.toString()
            progressBar.progress = i
        }

//        Thread.sleep(2500)
//        startActivity(Intent(this, LastFewUsers::class.java))
    }
}