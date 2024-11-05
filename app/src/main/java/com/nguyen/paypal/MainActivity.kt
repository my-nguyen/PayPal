package com.nguyen.paypal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nguyen.paypal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}