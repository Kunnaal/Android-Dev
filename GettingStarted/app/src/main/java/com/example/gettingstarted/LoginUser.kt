package com.example.gettingstarted

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginUser : AppCompatActivity() {

    private lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)

        val username = findViewById<EditText>(R.id.email_phone_edit_text)
        val password = findViewById<EditText>(R.id.password_edit_text)
        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<TextView>(R.id.register_button)
        val rememberMeCheckbox = findViewById<CheckBox>(R.id.remember_me_checkbox)

        // Set register onclick?
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterUser::class.java)
            startActivity(intent)
        }

        // Creating shared preferences of name "user_data"
        val sharedPreferences = this.getSharedPreferences(
            "user_data",
            Context.MODE_PRIVATE
        )

        // Creating an editor of shared preferences
        editor = sharedPreferences.edit()

        // Fetching the value of username and pass from shared preferences if exist
        val dbUsername = sharedPreferences.getString("username", null)
        val dbPassword = sharedPreferences.getString("password", null)

        // Set click listener for login button to check username & pass
        loginButton.setOnClickListener {
            if ((username.text.toString() == dbUsername) && (password.text.toString() == dbPassword)) {
                // Credentials entered by the user are correct, check if he has selected "remember me"
                val checkboxValue = rememberMeCheckbox.isChecked

                // If true, set the value in shared preferences to true
                if (checkboxValue) {
                    editor.putBoolean("keep_logged_in", true)
                    editor.commit()
                }

                // Now sent the user to main activity and end this one
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Credentials given by the users are wrong, raise a toast
                Toast.makeText(this, "Username or Password does not match!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}