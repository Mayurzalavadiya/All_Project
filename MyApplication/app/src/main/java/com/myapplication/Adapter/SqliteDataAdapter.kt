package com.myapplication.Adapter

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.Response.SqliteDataList
import com.myapplication.util.CommonMethodClass
import java.util.Locale

class SqliteDataAdapter(private val context: Context, private val arrayList: ArrayList<SqliteDataList>) :
    RecyclerView.Adapter<SqliteDataAdapter.MyHolder>() {

    private val searchList: ArrayList<SqliteDataList> = ArrayList()

    init {
        searchList.addAll(arrayList)
    }

    fun filter(s: String) {
        val lowerCaseQuery = s.toLowerCase(Locale.getDefault())
        arrayList.clear()
        if (lowerCaseQuery.isEmpty()) {
            arrayList.addAll(searchList)
        } else {
            for (l in searchList) {
                if (l.roll!!.toLowerCase(Locale.getDefault()).contains(lowerCaseQuery) ||
                    l.mark!!.toLowerCase(Locale.getDefault()).contains(lowerCaseQuery) ||
                    l.name!!.toLowerCase(Locale.getDefault()).contains(lowerCaseQuery)
                ) {
                    arrayList.add(l)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.custom_sqlite_recycler, parent, false)
        return MyHolder(view)
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var roll: TextView = itemView.findViewById(R.id.custom_sqlite_recycler_roll)
        var name: TextView = itemView.findViewById(R.id.custom_sqlite_recycler_name)
        var mark: TextView = itemView.findViewById(R.id.custom_sqlite_recycler_mark)
        var deleteIv: ImageView = itemView.findViewById(R.id.custom_sqlite_recycler_delete)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.roll.text = arrayList[position].roll
        holder.name.text = arrayList[position].name
        holder.mark.text = arrayList[position].mark

        holder.deleteIv.setOnClickListener {
            val db = SQLiteDatabase.openOrCreateDatabase("student", null)
            val deleteQuery =
                "delete from record WHERE roll='${arrayList[position].roll}'"
            Log.d("sqlite", "getView: ${arrayList[position].roll}")
            db.execSQL(deleteQuery)
            CommonMethodClass(context, "Delete Successfully")
            arrayList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}