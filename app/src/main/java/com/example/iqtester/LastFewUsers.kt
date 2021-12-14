package com.example.iqtester

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LastFewUsers : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var adapter: DisplayUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.last_few_users)

        initRecyclerView()

        val returnButton: Button = findViewById(R.id.returnToMainPage)
        returnButton.setOnClickListener {
            onBackPressed()
            finish()
        }
    }

    private fun getUsers() {
        val db = DBHelper(this)
        val items = db.getLastTenItems()
        adapter?.addItems(items)
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DisplayUser()
        recyclerView.adapter = adapter

        getUsers()
    }
}