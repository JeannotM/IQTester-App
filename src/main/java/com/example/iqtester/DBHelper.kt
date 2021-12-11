package com.example.iqtester

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.service.autofill.UserData
import android.widget.Toast

class DBHelper(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " (" +
                    ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_COl + " TEXT, " +
                    AGE_COL + " TEXT, " +
                    QUESTION_COL + " TEXT, " +
                    ANSWER_COL + " TEXT, " +
                    IQ_COL + " TEXT " +
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
    fun addUser(user: userData){
        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(NAME_COl, user.name)
        values.put(AGE_COL, user.age)
        values.put(QUESTION_COL, user.seenQuestions)
        values.put(ANSWER_COL, user.answersGiven)
        values.put(IQ_COL, user.iq)

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        val result = db.insert(TABLE_NAME, null, values)

        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): MutableList<userData> {
        val list: MutableList<userData> = ArrayList()
        val db = this.readableDatabase

//        val query = "SELECT name, age, iq FROM " + TABLE_NAME + " LIMIT 10"
//        val result = db.rawQuery(query, null)
//        if (result.moveToFirst()) {
//            do {
//                val name = result.getString(result.getColumnIndex(NAME_COl))
//                val age = result.getString(result.getColumnIndex(AGE_COL))
//                val iq = result.getString(result.getColumnIndex(IQ_COL))
//                val user = userData(name, age, iq, "", "")
//                list.add(user)
//            }
//            while (result.moveToNext())
//        }
        return list


        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
//        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
//        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " LIMIT 10", null)
    }

    companion object {
        // here we have defined variables for our database

        // below is variable for database name
        private const val DATABASE_NAME = "IQTesterApp"

        // below is the variable for table name
        const val TABLE_NAME = "user_data"

        // below is the variable for id column
        const val ID_COL = "id"

        // below is the variable for name column
        const val NAME_COl = "name"

        // below is the variable for age column
        const val AGE_COL = "age"

        // below is the variable for questions_seen column
        const val QUESTION_COL = "questions_seen"

        // below is the variable for chosen_answers column
        const val ANSWER_COL = "chosen_answers"

        // below is the variable for intelligence_quotient column
        const val IQ_COL = "intelligence_quotient"

        // below is the variable for date_created column
        const val DATE_COL = "date_created"
    }
}