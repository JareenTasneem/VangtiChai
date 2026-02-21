package com.example.vangtichai

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var amount = 0
    private lateinit var tvAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAmount = findViewById(R.id.tvAmount)

        val digitButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in digitButtons) {
            findViewById<Button>(id).setOnClickListener {
                val digit = (it as Button).text.toString().toInt()
                amount = amount * 10 + digit
                updateDisplay()
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            amount = 0
            updateDisplay()
        }
    }

    private fun calculateChange(value: Int): Map<Int, Int> {
        val notes = listOf(500,100,50,20,10,5,2,1)
        var remaining = value
        val result = mutableMapOf<Int,Int>()

        for (note in notes) {
            val count = remaining / note
            result[note] = count
            remaining %= note
        }
        return result
    }

    private fun updateChange() {
        val change = calculateChange(amount)

        findViewById<TextView>(R.id.tv500).text = "500: ${change[500]}"
        findViewById<TextView>(R.id.tv100).text = "100: ${change[100]}"
        findViewById<TextView>(R.id.tv50).text  = "50: ${change[50]}"
        findViewById<TextView>(R.id.tv20).text  = "20: ${change[20]}"
        findViewById<TextView>(R.id.tv10).text  = "10: ${change[10]}"
        findViewById<TextView>(R.id.tv5).text   = "5: ${change[5]}"
        findViewById<TextView>(R.id.tv2).text   = "2: ${change[2]}"
        findViewById<TextView>(R.id.tv1).text   = "1: ${change[1]}"
    }

    private fun updateDisplay() {
        tvAmount.text = "Taka: $amount"
        updateChange()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("amount", amount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        amount = savedInstanceState.getInt("amount")
        updateDisplay()
    }

}
