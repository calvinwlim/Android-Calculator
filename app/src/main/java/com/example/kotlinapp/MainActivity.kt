package com.example.kotlinapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnAdd: Button
    private lateinit var btnSub: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivision: Button
    private lateinit var btnExponent: Button
    private lateinit var btnRoot: Button
    private lateinit var btnLogarithm: Button
    private lateinit var btnE: Button
    private lateinit var btnPi: Button
    private lateinit var btnUsePrevResult: Button  // Button to use previous result
    private lateinit var etA: EditText
    private lateinit var etB: EditText
    private lateinit var resultTv: TextView

    private var previousResult: Double? = null  // Stores the last computed result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize buttons and views
        btnAdd = findViewById(R.id.btn_add)
        btnSub = findViewById(R.id.btn_subtraction)
        btnMultiply = findViewById(R.id.btn_multiplication)
        btnDivision = findViewById(R.id.btn_division)
        btnExponent = findViewById(R.id.btn_exponent)
        btnRoot = findViewById(R.id.btn_root)
        btnLogarithm = findViewById(R.id.btn_logarithm)
        btnE = findViewById(R.id.btn_e)
        btnPi = findViewById(R.id.btn_pi)
        btnUsePrevResult = findViewById(R.id.btn_use_prev_result)  // Initialize button
        etA = findViewById(R.id.et_a)
        etB = findViewById(R.id.et_b)
        resultTv = findViewById(R.id.result_tv)

        // Set button click listeners
        btnAdd.setOnClickListener(this)
        btnSub.setOnClickListener(this)
        btnMultiply.setOnClickListener(this)
        btnDivision.setOnClickListener(this)
        btnExponent.setOnClickListener(this)
        btnRoot.setOnClickListener(this)
        btnLogarithm.setOnClickListener(this)
        btnE.setOnClickListener(this)
        btnPi.setOnClickListener(this)
        btnUsePrevResult.setOnClickListener(this)  // Listener for using previous result
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_use_prev_result) {
            // Use the previous result as input for the next calculation
            if (previousResult != null) {
                etA.setText(previousResult.toString())
            } else {
                resultTv.text = "No previous result"
            }
            return
        }

        val aText = etA.text.toString()
        val bText = etB.text.toString()

        if (aText.isEmpty() && previousResult == null) {
            resultTv.text = "Please enter a number"
            return
        }

        val a = if (aText.isNotEmpty()) aText.toDouble() else previousResult ?: 0.0
        val b = if (bText.isNotEmpty()) bText.toDouble() else 0.0

        var result = 0.0

        when (v?.id) {
            R.id.btn_add -> result = a + b
            R.id.btn_subtraction -> result = a - b
            R.id.btn_multiplication -> result = a * b
            R.id.btn_division -> {
                if (b == 0.0) {
                    resultTv.text = "Cannot divide by zero"
                    return
                }
                result = a / b
            }
            R.id.btn_exponent -> result = a.pow(b)  // a^b
            R.id.btn_root -> {
                if (a < 0) {
                    resultTv.text = "Cannot compute square root of a negative number"
                    return
                }
                result = sqrt(a)  // √a
            }
            R.id.btn_logarithm -> {
                if (a <= 0 || b <= 0 || b == 1.0) {
                    resultTv.text = "Invalid input for logarithm"
                    return
                }
                result = ln(a) / ln(b)  // log_b(a)
            }
            R.id.btn_e -> result = Math.E  // Euler's number
            R.id.btn_pi -> result = Math.PI  // Pi
        }

        // Update the result text
        resultTv.text = "Result: $result"

        // Save result in memory for later use
        previousResult = result
    }
}