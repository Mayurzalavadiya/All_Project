package com.example.project1

import android.content.Context
import android.content.Intent
import android.widget.Toast

 class CommonMethod {
     companion object {

     fun showMessage(context:Context,message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

     fun moveToNextScreen(context:Context,nextClass: Class<*>) {
        val intent = Intent(context, nextClass)
        context.startActivity(intent)
    }


     }
 }