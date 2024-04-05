package com.myapplication.activity

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.util.CommonMethodClass
import com.myapplication.R

class Sqlite_Activity : AppCompatActivity() {


    private var rollEdit: EditText? = null
    private var nameEdit: EditText? = null
    private var markEdit: EditText? = null
    private var insertButton: Button? = null
    private var updateButton: Button? = null
    private var deleteButton: Button? = null
    private var showButton: Button? = null
    private var showRecyclerButton: Button? = null
    private var showCustomListButton: Button? = null
    private var navbar: Button? = null
    private var db: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

         db = openOrCreateDatabase("student", MODE_PRIVATE, null)
        val tableQuery =
            "create table if not exists record(roll int(5),name varchar(255),mark int(3))"
        db?.execSQL(tableQuery)
         rollEdit = findViewById<EditText>(R.id.sqlite_roll)
         nameEdit = findViewById<EditText>(R.id.sqlite_name)
         markEdit = findViewById<EditText>(R.id.sqlite_mark)
         insertButton = findViewById<Button>(R.id.sqlite_insert)
         updateButton = findViewById<Button>(R.id.sqlite_update)
         deleteButton  = findViewById<Button>(R.id.sqlite_delete)
         showButton = findViewById<Button>(R.id.sqlite_show)
         navbar = findViewById<Button>(R.id.navbar)
         showRecyclerButton = findViewById<Button>(R.id.sqlite_show_recycler)
         showCustomListButton  = findViewById<Button>(R.id.sqlite_show_custom_list)

        var searchButton:android.widget.Button? = findViewById<Button>(R.id.sqlite_search)

        searchButton!!.setOnClickListener {
            if (rollEdit!!.text.toString().trim { it <= ' ' }.equals("", ignoreCase = true)) {
                rollEdit!!.error = "Roll No Required"
            } else {
                val searchQuery =
                    "select * from record where roll='" + rollEdit!!.text.toString() + "'"
                val cursor = db!!.rawQuery(searchQuery, null)
                if (cursor.moveToFirst()) {
                    do {
                        rollEdit!!.setText(cursor.getString(0))
                        nameEdit!!.setText(cursor.getString(1))
                        markEdit!!.setText(cursor.getString(2))
                    } while (cursor.moveToNext())
                }
            }
        }

        showCustomListButton!!.setOnClickListener {
            CommonMethodClass(this, SqliteListviewActivity::class.java
            )
        }

        navbar!!.setOnClickListener {
            CommonMethodClass(this, MainActivity::class.java
            )
        }

        showRecyclerButton!!.setOnClickListener {
            CommonMethodClass(
                this,
                SqliteRecyclerviewActivity::class.java
            )
        }

        showButton!!.setOnClickListener {
            CommonMethodClass(
                this,
                SqliteListActivity::class.java
            )
        }

        deleteButton!!.setOnClickListener {
            if (rollEdit!!.text.toString().trim { it <= ' ' }.equals("", ignoreCase = true)) {
                rollEdit!!.error = "Roll No. Required"
            } else {
                val checkDataQuery =
                    "select * from record WHERE roll='" + rollEdit!!.text.toString() + "'"
                val cursor = db!!.rawQuery(checkDataQuery, null)
                if (cursor.count > 0) {
                    val deleteQuery =
                        "delete from record WHERE roll='" + rollEdit!!.text.toString() + "'"
                    db!!.execSQL(deleteQuery)
                    CommonMethodClass(this, "Delete Successfully")
                    clearData()
                } else {
                    CommonMethodClass(this, "Record Not Found")
                }
            }
        }

        updateButton!!.setOnClickListener {
            if (rollEdit!!.text.toString().trim { it <= ' ' }.equals("", ignoreCase = true)) {
                rollEdit!!.error = "Roll No Required"
            } else if (nameEdit!!.text.toString().trim { it <= ' ' }
                    .equals("", ignoreCase = true)) {
                nameEdit!!.error = "Name Required"
            } else if (markEdit!!.text.toString().trim { it <= ' ' }
                    .equals("", ignoreCase = true)) {
                markEdit!!.error = "Mark Required"
            } else {
                val checkDataQuery =
                    "select * from record WHERE roll='" + rollEdit!!.text.toString() + "'"
                val cursor = db!!.rawQuery(checkDataQuery, null)
                if (cursor.count > 0) {
                    val updateQuery =
                        "update record set name='" + nameEdit!!.text.toString() + "',mark='" + markEdit!!.text.toString() + "' WHERE roll='" + rollEdit!!.text.toString() + "'"
                    db!!.execSQL(updateQuery)
                    CommonMethodClass(this, "Update Successfully")
                    clearData()
                } else {
                    CommonMethodClass(this, "Record Not Found")
                }
            }
        }

        insertButton!!.setOnClickListener {
            if (rollEdit!!.text.toString().trim { it <= ' ' }.equals("", ignoreCase = true)) {
                rollEdit!!.error = "Roll No Required"
            } else if (nameEdit!!.text.toString().trim { it <= ' ' }
                    .equals("", ignoreCase = true)) {
                nameEdit!!.error = "Name Required"
            } else if (markEdit!!.text.toString().trim { it <= ' ' }
                    .equals("", ignoreCase = true)) {
                markEdit!!.error = "Mark Required"
            } else {
                val checkDataQuery =
                    "select * from record WHERE roll='" + rollEdit!!.text.toString() + "' "
                val cursor = db!!.rawQuery(checkDataQuery, null)
                if (cursor.count > 0) {
                    CommonMethodClass(this, "Record Already Exists")
                } else {
                    val insertQuery =
                        "insert into record VALUES('" + rollEdit!!.text.toString() + "','" + nameEdit!!.text.toString() + "','" + markEdit!!.text.toString() + "')"
                    db!!.execSQL(insertQuery)
                    CommonMethodClass(this, "Insert Successfully")
                    clearData()
                }
            }
        }

    }

    private fun clearData() {
        rollEdit!!.setText("")
        nameEdit?.setText("")
        markEdit?.setText("")
        rollEdit!!.requestFocus()
    }

}