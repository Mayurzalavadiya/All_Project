package com.task2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.task2.databinding.ActivityAddNewAddressBinding


@Suppress("DEPRECATION")
class AddNewAddressActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityAddNewAddressBinding

    private var addressType = arrayOf<String?>(
        "Home", "Work",
        "Office", "Other"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() = with(binding){
        imageViewBack.setOnClickListener{
            moveToNextPage(SignUpActivity::class.java)
        }
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) = with(binding) {
        if (position == 0) {
            (view as TextView).setTextColor(resources.getColor(R.color.orange))
        } else {
            (view as TextView).setTextColor(resources.getColor(R.color.black))
        }
        Toast.makeText(
            applicationContext,
            addressType[position],
            Toast.LENGTH_LONG
        )
            .show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun moveToNextPage(nextClass: Class<*>) {
        val intent = Intent(this, nextClass)
        startActivity(intent)
    }
}