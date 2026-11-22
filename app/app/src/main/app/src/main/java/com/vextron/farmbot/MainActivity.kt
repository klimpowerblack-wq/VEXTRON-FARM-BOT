
package com.vextron.farmbot

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*
import android.graphics.Typeface

class MainActivity : Activity() {

    private lateinit var status: TextView
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(24, 30, 24, 30)
            setBackgroundColor(Color.rgb(12, 12, 12))
        }

        val title = TextView(this).apply {
            text = "☠ VEXTRON\nFARM BOT"
            textSize = 32f
            gravity = Gravity.CENTER
            setTextColor(Color.RED)
            typeface = Typeface.DEFAULT_BOLD
        }

        status = TextView(this).apply {
            text = "СТАТУС: ОСТАНОВЛЕН"
            textSize = 18f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
        }

        root.addView(title)
        root.addView(status)

        addButton(root, "▶ ЗАПУСТИТЬ") {
            running = true
            status.text = "СТАТУС: ЗАПУЩЕН"
        }

        addButton(root, "Ⅱ ПАУЗА") {
            running = false
            status.text = "СТАТУС: ПАУЗА"
        }

        addButton(root, "■ ОСТАНОВИТЬ") {
            running = false
            status.text = "СТАТУС: ОСТАНОВЛЕН"
        }

        setContentView(root)
    }

    private fun addButton(
        root: LinearLayout,
        label: String,
        action: () -> Unit
    ) {
        val button = Button(this).apply {
            text = label
            textSize = 18f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.rgb(150, 0, 0))
            setOnClickListener { action() }
        }

        root.addView(
            button,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                65
            ).apply {
                setMargins(0, 12, 0, 12)
            }
        )
    }
}
