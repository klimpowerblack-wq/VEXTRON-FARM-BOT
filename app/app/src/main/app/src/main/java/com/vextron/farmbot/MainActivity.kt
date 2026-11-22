
package com.vextron.farmbot

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

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
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivity(intent)
            } else {
                startService(Intent(this, FloatingService::class.java))
                running = true
                status.text = "СТАТУС: ЗАПУЩЕН"
            }
        }

        addButton(root, "Ⅱ ПАУЗА") {
            running = false
            status.text = "СТАТУС: ПАУЗА"
        }

        addButton(root, "■ ОСТАНОВИТЬ") {
            running = false
            stopService(Intent(this, FloatingService::class.java))
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

