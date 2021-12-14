package com.example.iqtester

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.service.autofill.UserData
import android.widget.Toast

class DBHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        // here we have defined variables for our database
        private const val DATABASE_NAME = "IQTesterApp"
        const val TABLE_NAME = "user_data"
        const val ID_COL = "id"
        const val NAME_COL = "name"
        const val AGE_COL = "age"
        const val QUESTION_COL = "questions_seen"
        const val ANSWER_COL = "chosen_answers"
        const val IQ_COL = "intelligence_quotient"
        const val DATE_COL = "date_created"
        val QUESTIONS = Questions().getQuestions()
        val ANSWERS = Questions().getAnswers()
    }

    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " (" +
                    ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_COL + " TEXT, " +
                    AGE_COL + " TEXT, " +
                    QUESTION_COL + " TEXT, " +
                    ANSWER_COL + " TEXT, " +
                    IQ_COL + " TEXT, " +
                    DATE_COL + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
                    ");")

        // we are calling sqlite method for executing our query
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    // This method is for adding data in our database
    fun addUser(user: userData) {
        // below we are creating
        // a content values variable
        val values = ContentValues()

        var questions = ""
        var answers = ""


        for(i in user.inputValues) {
            if(questions !== "") {
                questions += ","
                answers += ","
            }
            questions += i.seenQuestions
            answers += i.answersGiven
        }

        // we are inserting our values
        // in the form of key-value pair
        values.put(NAME_COL, user.name)
        values.put(AGE_COL, user.age)
        values.put(QUESTION_COL, questions)
        values.put(ANSWER_COL, answers)
        values.put(IQ_COL, user.iq)

        // here we are creating a writable variable of
        // our database as we want to insert value in our database
        val db = this.writableDatabase

        val result = db.insert(TABLE_NAME, null, values)

        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }

        // at last we are closing our database
        db.close()
    }

    // below method is to get all data from our database
    fun getLastTenItems(): ArrayList<userData> {
        val list: ArrayList<userData> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME ORDER BY ID DESC LIMIT 10"

        val result: Cursor?

        try {
            result = db.rawQuery(query, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(query)
            return list
        }

        if (result.moveToFirst()) {
            do {
                val name = result.getString(result.getColumnIndex(NAME_COL))
                val age = result.getString(result.getColumnIndex(AGE_COL))
                val iq = result.getString(result.getColumnIndex(IQ_COL))
                val questions: Array<String> = result.getString(result.getColumnIndex(QUESTION_COL)).split(",").toTypedArray()
                val answers: Array<String> = result.getString(result.getColumnIndex(ANSWER_COL)).split(",").toTypedArray()
                val date = result.getString(result.getColumnIndex(DATE_COL))
                val inputValues: ArrayList<QuestionAnswer> = ArrayList()

                if(answers.size != 5) {
                    answers.set(answers.size - 1, "2")
                }

                for(i in 0..3) {
                    inputValues.add(
                        QuestionAnswer(
                            QUESTIONS.get(questions.get(i).toInt()),
                            ANSWERS.get(answers.get(i).toInt()),
                        )
                    )
                }

                val user = userData(name, age, iq, inputValues, date)
                list.add(user)
            }
            while (result.moveToNext())
        }

        db.close()
        return list
    }
}