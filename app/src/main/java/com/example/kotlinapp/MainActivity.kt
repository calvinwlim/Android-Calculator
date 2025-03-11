package com.example.kotlinapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var memory: Double? = null  // For saving previous results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set click listeners
        binding.btnAdd.setOnClickListener(this)
        binding.btnSubtraction.setOnClickListener(this)
        binding.btnMultiplication.setOnClickListener(this)
        binding.btnDivision.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val aText = binding.etA.text.toString()
        val bText = binding.etB.text.toString()

        // Allow memory to be used if one field is empty
        val a = if (aText.isNotEmpty()) aText.toDouble() else memory ?: 0.0
        val b = if (bText.isNotEmpty()) bText.toDouble() else 0.0

        var result = 0.0

        when (v?.id) {
            R.id.btnAdd -> result = a + b
            R.id.btnSubtraction -> result = a - b
            R.id.btnMultiplication -> result = a * b
            R.id.btnDivision -> result = if (b != 0.0) a / b else Double.NaN
        }

        // Save result in memory for future operations
        memory = result
        binding.resultTv.text = "Result: $result"
    }
}