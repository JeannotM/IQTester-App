package com.example.iqtester

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayUser : RecyclerView.Adapter<DisplayUser.UserViewholder>() {

    private var userList: ArrayList<userData> = ArrayList()
    private var onClickItem: ((userData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewholder (
        LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false)
    )

    fun addItems(items: ArrayList<userData>) {
        userList = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: UserViewholder, position: Int) {
        val user = userList[position]
        holder.bindView(user)
        onClickItem?.invoke(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewholder(var view: View): RecyclerView.ViewHolder(view) {
        private var name = view.findViewById<TextView>(R.id.userName)
        private var date = view.findViewById<TextView>(R.id.date)
        private var iq = view.findViewById<TextView>(R.id.iqScored)
        private var age = view.findViewById<TextView>(R.id.userAge)

        private var childRecyclerView: RecyclerView = itemView.findViewById(R.id.childRecyclerView)
        private var adapter: DisplayQuestion? = null

        fun bindView(user: userData) {
            name.text = user.name
            date.text = user.date
            age.text = (user.age + " jaar oud")
            iq.text = ("IQ of: " + user.iq)

            childRecyclerView.layoutManager = LinearLayoutManager(view.context)
            adapter = DisplayQuestion()
            childRecyclerView.adapter = adapter

            adapter?.addItems(user.inputValues)
        }
    }
}