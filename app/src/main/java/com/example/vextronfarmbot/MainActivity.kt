
package com.example.vextronfarmbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vextronfarmbot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or
                WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(
                bars.left,
                bars.top,
                bars.right,
                bars.bottom
            )
            insets
        }

        binding.startButton.setOnClickListener {
            binding.status.text = "● БОТ ЗАПУЩЕН"
            binding.status.setTextColor(Color.GREEN)
        }

        binding.stopButton.setOnClickListener {
            binding.status.text = "● БОТ ОСТАНОВЛЕН"
            binding.status.setTextColor(Color.RED)
        }
    }
}
