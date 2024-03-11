package com.example.basics.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basics.R
import com.example.basics.ui.theme.BasicsTheme

class MainActivity : ComponentActivity() {

    private var employee_id: EditText? = null
    private var employee_name: EditText? = null
    private var employee_number: EditText? = null
    private var employee_city: EditText? = null
    private var employee_salary: EditText? = null
    private var employee_submit: Button? = null

    var employee_details = mutableListOf<Any>()

    var hashMap: HashMap<String, String> = HashMap<String, String>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        employee_id = findViewById(R.id.edittext_id)
        employee_name = findViewById(R.id.edittext_name)
        employee_number = findViewById(R.id.edittext_number)
        employee_city = findViewById(R.id.edittext_city)
        employee_salary = findViewById(R.id.edittext_salary)
        employee_submit = findViewById(R.id.button_submit)
        employee_submit!!.setOnClickListener {
            val id = employee_id!!.text.toString().trim()
            val name = employee_name!!.text.toString().trim()
            val number = employee_number!!.text.toString().trim()
            val city = employee_city!!.text.toString().trim()
            val salary = employee_salary!!.text.toString().trim()

            if (id == "") {
                Toast.makeText(this, "Please enter id", Toast.LENGTH_SHORT).show()
            } else if (name == "") {
                Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show()
            } else if (number == "") {
                Toast.makeText(this, "Please enter number", Toast.LENGTH_SHORT).show()
            } else if (number.length !== 10) {
                Toast.makeText(this, "Please enter valid number", Toast.LENGTH_SHORT).show()
            } else if (city == "") {
                Toast.makeText(this, "Please enter city name", Toast.LENGTH_SHORT).show()
            } else if (salary == "") {
                Toast.makeText(this, "Please enter salary", Toast.LENGTH_SHORT).show()
            } else {
                hashMap.put("id", id)
                hashMap.put("name", name)
                hashMap.put("number", number)
                hashMap.put("city", city)
                hashMap.put("salary", salary)

                employee_details.add(hashMap)


                employee_id!!.setText("")
                employee_name!!.setText("")
                employee_number!!.setText("")
                employee_city!!.setText("")
                employee_salary!!.setText("")
            }
        }
    }

}


