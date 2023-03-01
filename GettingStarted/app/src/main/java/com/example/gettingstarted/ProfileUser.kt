package com.example.gettingstarted

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class ProfileUser : AppCompatActivity() {

    private lateinit var editor : Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_user)

        val userName = findViewById<TextView>(R.id.user_name)
        val userId = findViewById<TextView>(R.id.user_id)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val logoutButton = findViewById<Button>(R.id.logout_button)

        // Creating shared preferences of name "user_data"
        val sharedPreferences = this.getSharedPreferences(
            "user_data",
            Context.MODE_PRIVATE
        )
        // Creating an editor of shared preferences
        editor = sharedPreferences.edit()

        // Fetching the value of username and pass from shared preferences if exist
        val dbUsername = sharedPreferences.getString("username", null)
        val dbName = sharedPreferences.getString("name", null)

        //Placing the fetched values in activity
        userName.text = dbName
        userId.text = dbUsername

        // Go back on back button press
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Logout button on click listener, remove all the data stored in shared
        // preference storage.
        logoutButton.setOnClickListener {
            // Clear everything and commit changes
            editor.clear().commit()

            // Start login activity and finish this one
            val intent = Intent(this, LoginUser::class.java)
            startActivity(intent)
            finish()
        }
    }
}