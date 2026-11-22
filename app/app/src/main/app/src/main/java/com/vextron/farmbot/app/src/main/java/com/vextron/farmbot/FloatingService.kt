
package com.vextron.farmbot

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout

class FloatingService : Service() {

    private lateinit var windowManager: WindowManager
    private var floatingButton: Button? = null

    override fun onCreate() {
        super.onCreate()

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        val button = Button(this).apply {
            text = "☠ V"
            textSize = 20f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.RED)
            setOnClickListener {
                val intent = Intent(this@FloatingService, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        val params = WindowManager.LayoutParams(
            140,
            140,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            else
                WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.END
        params.x = 20
        params.y = 300

        windowManager.addView(button, params)
        floatingButton = button
    }

    override fun onDestroy() {
        floatingButton?.let {
            windowManager.removeView(it)
        }
        floatingButton = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
