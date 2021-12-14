package com.example.iqtester

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DisplayQuestion : RecyclerView.Adapter<DisplayQuestion.ItemViewHolder>() {

    private var itemList: ArrayList<QuestionAnswer> = ArrayList()
    private var onClickItem: ((QuestionAnswer) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.question_answer_style, parent, false)
    )

    fun addItems(items: ArrayList<QuestionAnswer>) {
        itemList = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.bindView(item)
        onClickItem?.invoke(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        private var question = view.findViewById<TextView>(R.id.questionField)
        private var answer = view.findViewById<TextView>(R.id.answerField)

        fun bindView(field: QuestionAnswer) {
            question.text = field.seenQuestions
            answer.text = field.answersGiven
        }
    }
}