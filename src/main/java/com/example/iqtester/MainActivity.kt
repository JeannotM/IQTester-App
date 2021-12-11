package com.example.iqtester

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterUserDataButton: Button = findViewById(R.id.start_quiz)
        val db = DBHelper(this)
        enterUserDataButton.setOnClickListener {
            startActivity(Intent(this, EnterUserData::class.java))
//            db.addUser(userData("hendrika", "a", "", "", ""))
        }
    }
}