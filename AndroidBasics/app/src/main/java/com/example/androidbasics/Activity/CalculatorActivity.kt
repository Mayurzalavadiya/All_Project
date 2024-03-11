package com.example.androidbasics.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.example.androidbasics.R


class CalculatorActivity : AppCompatActivity() {
    private var btnClear: AppCompatButton? = null
    private var btnAdd: AppCompatButton? = null
    private var btnSub: AppCompatButton? = null
    private var btnMulti: AppCompatButton? = null
    private var btnDiv: AppCompatButton? = null
    private var btnEqual: AppCompatButton? = null
    private var btnDot: AppCompatButton? = null
    private var btnPer: AppCompatButton? = null

    private var btn0: AppCompatButton? = null
    private var btn1: AppCompatButton? = null
    private var btn2: AppCompatButton? = null
    private var btn3: AppCompatButton? = null
    private var btn4: AppCompatButton? = null
    private var btn5: AppCompatButton? = null
    private var btn6: AppCompatButton? = null
    private var btn7: AppCompatButton? = null
    private var btn8: AppCompatButton? = null
    private var btn9: AppCompatButton? = null

    private var textView: AppCompatTextView? = null
    private var currentText: Double = 0.0
    private var secondText: Double = 0.0
    private var count = 0
    private var operation: String = ""


    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        textView = findViewById(R.id.textview)
        btnClear = findViewById(R.id.buttonClear)
        btnEqual = findViewById(R.id.buttonEqual)
        btnAdd = findViewById(R.id.buttonAddition)
        btnSub = findViewById(R.id.buttonSub)
        btnMulti = findViewById(R.id.buttonMulti)
        btnDiv = findViewById(R.id.buttonDiv)
        btnDot = findViewById(R.id.buttonDot)
        btnPer = findViewById(R.id.buttonPercentage)
        btn0 = findViewById(R.id.button0)
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)
        btn4 = findViewById(R.id.button4)
        btn5 = findViewById(R.id.button5)
        btn6 = findViewById(R.id.button6)
        btn7 = findViewById(R.id.button7)
        btn8 = findViewById(R.id.button8)
        btn9 = findViewById(R.id.button9)


        btn0?.setOnClickListener() { numberClick("0") }
        btn1?.setOnClickListener() { numberClick("1") }
        btn2?.setOnClickListener() { numberClick("2") }
        btn3?.setOnClickListener() { numberClick("3") }
        btn4?.setOnClickListener() { numberClick("4") }
        btn5?.setOnClickListener() { numberClick("5") }
        btn6?.setOnClickListener() { numberClick("6") }
        btn7?.setOnClickListener() { numberClick("7") }
        btn8?.setOnClickListener() { numberClick("8") }
        btn9?.setOnClickListener() { numberClick("9") }
        btnDot?.setOnClickListener() {
            var str = textView?.text.toString().trim();
            if (str.length > 0) {
                if (count == 0) {
                    numberClick(".")
                    count = 1
                }

            } else {
                numberClick("0.")
                count = 1
            }
        }


        btnAdd?.setOnClickListener() {

            operation("+")
        }
        btnSub?.setOnClickListener() { operation("-") }
        btnMulti?.setOnClickListener() { operation("*") }
        btnDiv?.setOnClickListener() { operation("/") }
        btnPer?.setOnClickListener() { operation("%") }

        btnEqual?.setOnClickListener { calculateResult() }

        btnClear?.setOnClickListener { clearCalculator() }
    }

    private fun numberClick(number: String) {
        val currentText = textView?.text.toString()
        textView?.text = currentText + number
    }

    private fun operation(operator: String) {
        count = 0
        if (textView?.text.toString() != "") {
            operation = operator
            currentText = textView?.text.toString().toDouble()
            textView?.text = ""
        }
    }

    private fun calculateResult() {
        count = 1
        if (textView?.text.toString() != "") {
            if (secondText.equals(0.0)) {
                secondText = textView?.text.toString().toDouble()
            } else {
                currentText = textView?.text.toString().toDouble()
            }
        }


        var result = 0.0

        when (operation) {
            "+" -> result = currentText + secondText
            "-" -> result = currentText - secondText
            "*" -> result = currentText * secondText
            "/" -> result = currentText / secondText
            "%" -> {
                result = if (secondText.equals(0.0)) {
                    (currentText * 1) / 100
                } else {
                    (currentText * secondText) / 100
                }
            }
        }

        textView?.text = result.toString()
    }

    private fun clearCalculator() {
        textView?.text = ""
        currentText = 0.0
        operation = ""
        secondText = 0.0
        count = 0
    }
}

