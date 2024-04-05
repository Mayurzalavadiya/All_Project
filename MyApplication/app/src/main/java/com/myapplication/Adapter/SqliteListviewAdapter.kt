package com.myapplication.Adapter

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.myapplication.R
import com.myapplication.Response.SqliteDataList
import com.myapplication.util.CommonMethodClass
import java.util.Locale

class SqliteListviewAdapter(
    private val context: Context,
    private val arrayList: ArrayList<SqliteDataList>
) : BaseAdapter() {
    private val searchList: ArrayList<SqliteDataList> = ArrayList()

    private lateinit var db: SQLiteDatabase


    init {
        searchList.addAll(arrayList)
    }

    fun filter(s: String) {
        val filterText = s.toLowerCase(Locale.getDefault())
        arrayList.clear()
        if (filterText.isEmpty()) {
            arrayList.addAll(searchList)
        } else {
            for (l in searchList) {
                if (l.roll!!.toLowerCase(Locale.getDefault()).contains(filterText)
                    || l.mark!!.toLowerCase(Locale.getDefault()).contains(filterText)
                    || l.name!!.toLowerCase(Locale.getDefault()).contains(filterText)
                ) {
                    arrayList.add(l)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(i: Int, view: View?, viewgroup: ViewGroup?): View {
        var view = view
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.custom_sqlite_list, null)
        val rollNo = view.findViewById<TextView>(R.id.custom_sqlite_list_roll)
        val name = view.findViewById<TextView>(R.id.custom_sqlite_list_name)
        val mark = view.findViewById<TextView>(R.id.custom_sqlite_list_mark)
        val delete = view.findViewById<ImageView>(R.id.custom_sqlite_list_delete)

        val dataItem = arrayList[i]


        rollNo.text = arrayList[i].roll
        name.text = arrayList[i].name
        mark.text = arrayList[i].mark


        delete.setOnClickListener {
            val db = SQLiteDatabase.openOrCreateDatabase("student", null)
            val deleteQuery =
                "delete from record WHERE roll='${arrayList[i].roll}'"
            Log.d("sqlite", "getView: ${arrayList[i].roll}")
            db.execSQL(deleteQuery)
            CommonMethodClass(context, "Delete Successfully")
            arrayList.removeAt(i)
            notifyDataSetChanged()
        }

        return view
    }

}