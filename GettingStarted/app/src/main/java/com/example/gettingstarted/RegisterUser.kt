package com.example.gettingstarted

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterUser : AppCompatActivity() {

    private lateinit var editor : Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        val username = findViewById<EditText>(R.id.email_phone_edit_text)
        val name = findViewById<EditText>(R.id.your_name_edit_text)
        val password = findViewById<EditText>(R.id.password_edit_text)
        val confirmPassword = findViewById<EditText>(R.id.confirm_password_edit_text)
        val registerButton = findViewById<Button>(R.id.register_button)
        val loginButton = findViewById<TextView>(R.id.login_button)

        // Applying on click listener for login button
        loginButton.setOnClickListener {
            onBackPressed()
        }

        // Creating shared preferences of name "user_data"
        val sharedPreferences = this.getSharedPreferences(
            "user_data",
            Context.MODE_PRIVATE
        )

        // Creating an editor of shared preferences
        editor = sharedPreferences.edit()

        registerButton.setOnClickListener {
            if (password.text.toString() == confirmPassword.text.toString()) {
                updateLocalSharedStorage(username.text.toString(), name.text.toString(), password.text.toString())
            } else {
                Toast.makeText(this, "Passwords do not match!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateLocalSharedStorage(username : String, name : String, password : String) {

        // Add string to editor
        editor.putString("username", username)
        editor.putString("name", name)
        editor.putString("password", password)
        editor.putBoolean("keep_logged_in", false) // Adding a default keep logged in value

        // Commit the editor
        editor.commit()
        Log.d("updateLocalSharedStorage", "Committed data to shared storage \"user_data\"")

        // Send user directly to main activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}