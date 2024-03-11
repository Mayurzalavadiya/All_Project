package com.myapplication.activity

import android.app.AlertDialog
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.myapplication.R
import android.content.DialogInterface
import android.view.View
import android.widget.AdapterView
import com.myapplication.util.CommonMethodClass

class SqliteListActivity : AppCompatActivity() {

    private lateinit var db: SQLiteDatabase
    private lateinit var listView: ListView
    private lateinit var arrayList: ArrayList<String>
    private lateinit var arrayRollNoList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_list)
//        supportActionBar?.hide()
        db = openOrCreateDatabase("student", MODE_PRIVATE, null)
        val tableQuery = "create table if not exists record(roll int(5),name varchar(255),mark int(3))"
        db.execSQL(tableQuery)

        listView = findViewById(R.id.sqlite_list)

        val selectQuery = "select * from record"
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            arrayList = ArrayList()
            arrayRollNoList = ArrayList()
            do {
                arrayRollNoList.add(cursor.getString(0))
                arrayList.add(
                    "Roll No : ${cursor.getString(0)}\nName : ${cursor.getString(1)}\nMark : ${cursor.getString(2)}"
                )
            } while (cursor.moveToNext())

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
            listView.adapter = adapter

            listView.setOnItemLongClickListener { adapterView: AdapterView<*>, view: View, position: Int, l: Long ->
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Delete Record!")
                builder.setMessage("Are You Sure Want to delete Record?")
                builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                    val deleteQuery = "delete from record WHERE roll='${arrayRollNoList[position]}'"
                    db.execSQL(deleteQuery)
                    CommonMethodClass(this, "Delete Successfully")
                    arrayRollNoList.removeAt(position)
                    arrayList.removeAt(position)
                    adapter.notifyDataSetChanged()
                }
                builder.setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
                    dialogInterface.dismiss()
                }
                builder.show()
                false
            }
        }
    }
}