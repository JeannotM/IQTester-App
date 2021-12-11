package com.example.iqtester

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.security.SecureRandom


class QuizQuestions : AppCompatActivity() {
    var db: DBHelper = DBHelper(this)
    var questionCount: Int = 0
    var seenQuestions = ArrayList<Int>()
    var chosenAnswers = ArrayList<Int>()
    val questions = arrayOf(
            "Vind jij dat God bestaat?", "Vind jij dat we Socialisme verder moeten uitbreiden?", "Vind jij dat we Communisme terug moeten brengen?", "Vind jij dat Furries het recht moeten hebben om te stemmen?",
            "Moeten mensen met Autisme gewoon normaal leren doen?", "Heb jij vandaag de 5 gebeden al gedaan?", "Was Brexit rechtvaardig?", "Had Trump toch gelijk?",
            "Is klimaatverandering wel echt?", "Is de zin: 'Doe maar wat je wilt' een valstrik?", "Bestaat vrijheid van meningsuiting nog vandaag de dag nog?", "Is geld echt zo belangrijk?",
            "Geld kan geen geluk kopen, maar zou je liever huilen in een Ferrari?", "Is CyberBullying altijd slecht?", "Zijn atleten wel daadwerkelijk rolmodelen?", "Geeft ObamaCare wel echt de zorg die Amerika nodig heeft?",
            "Hoe kunnen dingen zijn als ze niet zijn?", "has anyone really been far even as decided to use even go want to do more look like?"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_question)
        val nextQuestion: Button = findViewById(R.id.next_question)

        nextQuestion.setOnClickListener {
            loadNextQuestion()
        }

        loadNextQuestion()
    }

    private fun loadNextQuestion() {
        if(questionCount >= 5) {
            onQuizFinished()
        } else {
            val firstAnswer: RadioButton = findViewById(R.id.answer_1)
            val secondAnswer: RadioButton = findViewById(R.id.answer_2)
            val thirdAnswer: RadioButton = findViewById(R.id.answer_3)
            val fourthAnswer: RadioButton = findViewById(R.id.answer_4)
            val fifthAnswer: RadioButton = findViewById(R.id.answer_5)

            if (firstAnswer.isChecked) {
                firstAnswer.isChecked = false
                chosenAnswers.add(1)
            } else if (secondAnswer.isChecked) {
                secondAnswer.isChecked = false
                chosenAnswers.add(2)
            } else if (thirdAnswer.isChecked) {
                thirdAnswer.isChecked = false
                chosenAnswers.add(3)
            } else if (fourthAnswer.isChecked) {
                fourthAnswer.isChecked = false
                chosenAnswers.add(4)
            } else if (fifthAnswer.isChecked) {
                fifthAnswer.isChecked = false
                chosenAnswers.add(5)
            } else if (questionCount != 0) {
                Toast.makeText(this, "Je moet eerst een antwoord kiezen", Toast.LENGTH_SHORT).show()
                return
            }

            questionCount++
            var randomNumber: Int = SecureRandom().nextInt(questions.size)
            while (seenQuestions.contains(randomNumber))
                randomNumber = SecureRandom().nextInt(questions.size)

            seenQuestions.add(randomNumber)
            val randomQuestion: String = questions[randomNumber]

            val questionField: TextView = findViewById(R.id.question_view)
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

        val IQ = 40 + SecureRandom().nextInt(120)

        db.addUser(userData(name, age, IQ.toString(), seenQuestions.joinToString(","), chosenAnswers.joinToString(",")))

        val intent = Intent(this, QuizFinished::class.java)

        intent.putExtra("name", name)
        intent.putExtra("iq", IQ)

        startActivity(intent)

    }
}