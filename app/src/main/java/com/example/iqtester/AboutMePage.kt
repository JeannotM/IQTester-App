package com.example.iqtester

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AboutMePage: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_me_page)

        val returnButton: Button = findViewById(R.id.returnToMainPage)
        returnButton.setOnClickListener {
            onBackPressed()
        }
    }
}
