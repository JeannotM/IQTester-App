package com.example.iqtester

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LastFewUsers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.last_few_users)

        Thread { getUsers() }
    }

    fun getUsers() {
        val db = DBHelper(this)
        val data = db.getName()
        val tmpText: TextView = findViewById(R.id.tmp)
        tmpText.text = ""
        for (i in 0 until data.size) {
            tmpText.append(
                    data[i].name + " " + data[i].age + " " + data[i].iq + "\n"
            )
        }
    }
}