package com.example.gettingstarted

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

class SplashScreen : AppCompatActivity() {

    private lateinit var intent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // Creating shared preferences of name "user_data"
        val sharedPreferences = this.getSharedPreferences(
            "user_data",
            Context.MODE_PRIVATE
        )

        // Check if remember me stored in db as true
        val rememberMe = sharedPreferences.getBoolean("keep_logged_in", false)

        Handler (Looper.getMainLooper()).postDelayed({

            // Assigning intent based on value of remember me stored in shared preferences
            // to redirect to either login or home page based on checkbox
            intent = if (rememberMe) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LoginUser::class.java)
            }
            startActivity(intent)
            finish()
        }, 2000)
    }
}