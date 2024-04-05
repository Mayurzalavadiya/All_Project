package com.example.listviewdemo.demo.list.activity

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.example.listviewdemo.demo.list.adapter.CustomArrayAdapter
import com.example.listviewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val listString = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I")


        val listInt = ArrayList<Int>()

        for (i in 0..1000000) {
//
            val textView = TextView(this)
            textView.text = i.toString()
            textView.textSize = 20f
            textView.setPadding(0, 5, 0, 5);
            textView.typeface = Typeface.DEFAULT_BOLD;
            textView.gravity = Gravity.CENTER
            textView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )

            binding.linerLayoutMain
                .addView(textView)

        }

//        val arrayAdapter = MyAdapter(this,listString)
        val arrayAdapter = CustomArrayAdapter(listInt)
//        val arrayAdapter: ArrayAdapter<Int> = ArrayAdapter(this, R.layout.custem_list_view,R.id.textViewName,listInt)

//        binding.listView.addHeaderView(
//            LayoutInflater.from(this).inflate(R.layout.list_header, binding.listView, false)
//        )
//        binding.listView.addFooterView(
//            LayoutInflater.from(this).inflate(R.layout.list_footer, binding.listView, false)
//        )
//        binding.listView.adapter = arrayAdapter
//        arrayAdapter.addAll(*listInt)

        giveMeVarargs(5, 2, 3, 5, 3546544, 5, 5, 545, 455, 454)

//        binding.linerLayoutMain.addView()

    }

    private fun giveMeVarargs(vararg xyz: Int) {

        val abc = arrayListOf<Int>()

        xyz.toCollection(abc)

        abc.forEach {
            println(it)
        }
//       xyz.forEach {
//           abc.add(it)
//       }


    }
}

