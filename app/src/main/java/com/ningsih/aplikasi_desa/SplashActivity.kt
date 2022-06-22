package com.ningsih.aplikasi_desa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ningsih.aplikasi_desa.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            finish()

            val toMainPage = Intent(this, MainActivity::class.java)
            startActivity(toMainPage)
        }, 3000)
    }
}