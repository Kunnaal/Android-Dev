package com.example.gettingstarted.activityLifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.gettingstarted.R

class ActA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act)

        Log.d("onCreateA: ", "onCreate Called")

        findViewById<Button>(R.id.btn_acta).setOnClickListener() {
            val intent = Intent(this, ActB::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("onStartA: ", "onStart Called")
    }

    override fun onResume() {
        super.onResume()

        Log.d("onResumeA: ", "onResume Called")
    }

    override fun onPause() {
        super.onPause()

        Log.d("onPauseA: ", "onPause Called")
    }

    override fun onStop() {
        super.onStop()

        Log.d("onStopA: ", "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("onDestroyA: ", "onDestroy Called")
    }
}