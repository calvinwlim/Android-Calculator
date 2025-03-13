package com.example.kotlinapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinapp.databinding.ActivityMainBinding
import kotlin.math.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var memory: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener(this)
        binding.btnSubtraction.setOnClickListener(this)
        binding.btnMultiplication.setOnClickListener(this)
        binding.btnDivision.setOnClickListener(this)
        binding.btnRoot.setOnClickListener(this)
        binding.btnExponent.setOnClickListener(this)
        binding.btnLog.setOnClickListener(this)
        binding.btnPi.setOnClickListener(this)
        binding.btnE.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val aText = binding.etA.text.toString()
        val bText = binding.etB.text.toString()

        val a = if (aText.isNotEmpty()) aText.toDouble() else memory ?: 0.0
        val b = if (bText.isNotEmpty()) bText.toDouble() else 0.0
        var result = 0.0

        when (v?.id) {
            R.id.btnAdd -> result = a + b
            R.id.btnSubtraction -> result = a - b
            R.id.btnMultiplication -> result = a * b
            R.id.btnDivision -> result = if (b != 0.0) a / b else Double.NaN
            R.id.btnRoot -> result = if (a >= 0) sqrt(a) else Double.NaN
            R.id.btnExponent -> result = a.pow(b)
            R.id.btnLog -> result = if (a > 0) log10(a) else Double.NaN
            R.id.btnPi -> result = PI
            R.id.btnE -> result = E
        }
        memory = result
        binding.resultTv.text = "Result: $result"
    }
}