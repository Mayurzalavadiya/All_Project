package com.myapplication.activity

import android.annotation.SuppressLint
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.myapplication.Adapter.SqliteListviewAdapter
import com.myapplication.R
import com.myapplication.Response.SqliteDataList

class SqliteListviewActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var arrayList: ArrayList<SqliteDataList>
    private lateinit var adapter: SqliteListviewAdapter
    private lateinit var db: SQLiteDatabase
    private lateinit var searchView: SearchView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_listview)
        listView = findViewById(R.id.sqlite_listview)
        searchView = findViewById(R.id.sqlite_listview_searchview)


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.trim().equals("", ignoreCase = true)) {
                    adapter.filter("")
                } else {
                    adapter.filter(newText)
                }
                return false
            }
        })

        db = openOrCreateDatabase("student", MODE_PRIVATE, null)
        val tableQuery =
            "create table if not exists record(roll int(5),name varchar(255),mark int(10))"
        db.execSQL(tableQuery)

        val selectQuery = "select * from record"
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            arrayList = ArrayList()
            do {
                val list = SqliteDataList()
                list.roll = cursor.getString(0)
                list.name = cursor.getString(1)
                list.mark = cursor.getString(2)
                arrayList.add(list)
            } while (cursor.moveToNext())
            adapter = SqliteListviewAdapter(this@SqliteListviewActivity, arrayList)
            listView.adapter = adapter
        }
    }
}