package com.example.iqtester

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.security.SecureRandom


class QuizQuestions : AppCompatActivity() {
    private var db: DBHelper = DBHelper(this)
    private var questionCount: Int = 0
    private var inputValues = ArrayList<QuestionAnswer>()
    private var seenQuestions: ArrayList<Int> = ArrayList()
    private val questions: Array<String> = Questions().getQuestions()
    private lateinit var questionField: TextView
    private lateinit var radioButton: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_question)
        val nextQuestion: Button = findViewById(R.id.next_question)

        initView()

        nextQuestion.setOnClickListener {
            loadNextQuestion()
        }

        loadNextQuestion()
    }

    private fun initView() {
        questionField = findViewById(R.id.question_view)
        radioButton = findViewById(R.id.radioGroup)
    }

    private fun loadNextQuestion() {
        val input: Int = radioButton.checkedRadioButtonId

        if(input == -1 && questionCount != 0) {
            Toast.makeText(this, "Je moet eerst een antwoord kiezen", Toast.LENGTH_SHORT).show()
            return
        }

        radioButton.clearCheck()

        if(questionCount != 0) {
            inputValues.add(QuestionAnswer(
                    seenQuestions.get(seenQuestions.size - 1).toString(),
                    (input - 1).toString()
                )
            )
        }

        if(questionCount >= 5) {
            onQuizFinished()
        } else {
            questionCount++
            var randomNumber: Int = SecureRandom().nextInt(questions.size)
            while (seenQuestions.contains(randomNumber))
                randomNumber = SecureRandom().nextInt(questions.size)

            seenQuestions.add(randomNumber)
            val randomQuestion: String = questions[randomNumber]

            questionField.text = ("$questionCount: $randomQuestion")
        }
    }

    private fun onQuizFinished() {
        val extras = intent.extras
        var name = ""
        var age = ""

        if(extras != null) {
            name = extras.getString("name").toString()
            age = extras.getString("age").toString()
        }

        var IQ = 40 + SecureRandom().nextInt(120)

        for(i in inputValues)
            if(i.answersGiven === "2")
                IQ -= 2

        db.addUser(userData(name, age, IQ.toString(), inputValues, ""))

        val intent = Intent(this, QuizFinished::class.java)

        intent.putExtra("name", name)
        intent.putExtra("iq", IQ)

        startActivity(intent)
        finish()
    }
}