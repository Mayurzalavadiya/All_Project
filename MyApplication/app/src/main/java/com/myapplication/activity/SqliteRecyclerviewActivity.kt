package com.myapplication.activity

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.Adapter.SqliteDataAdapter
import com.myapplication.R
import com.myapplication.Response.SqliteDataList

class SqliteRecyclerviewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<SqliteDataList>
    private lateinit var adapter: SqliteDataAdapter
    private lateinit var db: SQLiteDatabase
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_recyclerview)

        recyclerView = findViewById(R.id.sqlite_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()

        searchView = findViewById(R.id.sqlite_searchview)

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
        val tableQuery = "create table if not exists record(roll int(5),name varchar(255),mark int(3))"
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
            adapter = SqliteDataAdapter(this, arrayList)
            recyclerView.adapter = adapter
        }
    }
}