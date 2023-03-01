package com.example.gettingstarted.fragmentLifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.gettingstarted.R

class FragmentLifecycle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmant_lifecycle)

        Log.d("onCreateA: ", "onCreate Activity Called")

        var dogFragment = DogFragment()
        var catFragment = CatFragment()

        findViewById<Button>(R.id.dog_fragment_btn).setOnClickListener() {

            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame_layout, dogFragment)
                .commit()

            Log.d(this.toString(), "Dog button clicked...")
        }

        findViewById<Button>(R.id.cat_fragment_btn).setOnClickListener{
            Log.d(this.toString(), "Cat button clicked...")

            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame_layout, catFragment)
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStartA: ", "onStart Activity Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResumeA: ", "onResume Activity Activity Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPauseA: ", "onPause Activity Called")
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