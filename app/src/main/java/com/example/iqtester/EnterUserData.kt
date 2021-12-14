package com.example.iqtester

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EnterUserData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_user_data)

        val enterUserDataButton: Button = findViewById(R.id.start_quiz)
        enterUserDataButton.setOnClickListener {
            onButtonClick()
            finish()
        }
    }

    fun onButtonClick() {
        val userName: EditText = findViewById(R.id.name)
        val userAge: EditText = findViewById(R.id.age)

        val name = userName.text.toString()
        val age = userAge.text.toString()

        if(name.isEmpty()) {
            Toast.makeText(this, "Uw naam is nog leeg", Toast.LENGTH_SHORT).show()
        } else if (age.isEmpty()) {
            Toast.makeText(this, "Uw leeftijd is nog onbekend", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, QuizQuestions::class.java)

            intent.putExtra("name", name)
            intent.putExtra("age", age)

            startActivity(intent)
        }
    }

}