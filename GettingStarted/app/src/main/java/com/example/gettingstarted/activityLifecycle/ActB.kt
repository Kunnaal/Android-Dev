package com.example.gettingstarted.activityLifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gettingstarted.R

class ActB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_b)

        Log.d("onCreateB: ", "onCreate Called")
    }

    override fun onStart() {
        super.onStart()

        Log.d("onStartB: ", "onStart Called")
    }

    override fun onResume() {
        super.onResume()

        Log.d("onResumeB: ", "onResume Called")
    }
}