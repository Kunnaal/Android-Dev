package com.example.loginregister

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameInput = findViewById<EditText>(R.id.username_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val submitButton = findViewById<Button>(R.id.submit_btn)
        val eyeToggle = findViewById<ImageButton>(R.id.password_eye)

        eyeToggle.setOnClickListener() {
            if (passwordInput.transformationMethod == PasswordTransformationMethod.getInstance())
                passwordInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
            else
                passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()
        }

        passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()

        // Creating shared preferences of name "user_data"
        val sharedPreferences = this.getSharedPreferences(
            "user_data",
            Context.MODE_PRIVATE
        )

        // Creating an editor of shared preferences
        val editor = sharedPreferences.edit()

        submitButton.setOnClickListener() {
            //Fetch values from input fields
            var username = usernameInput.text.toString()
            var password = passwordInput.text.toString()

            // Add data to shared preference
            editor.putString(username, password)
            editor.apply()
            // editor.commit()

            // Clear data after it is sent
            usernameInput.text.clear()
            passwordInput.text.clear()
        }

    }

    override fun onResume() {
        super.onResume()

        val usernameInput = findViewById<EditText>(R.id.username_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)

        passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()

        // Creating shared preferences of name "user_data"
        val sharedPreferences = this.getSharedPreferences(
            "user_data",
            Context.MODE_PRIVATE
        )

        var value = sharedPreferences.getString("Kunnaal", null)

        usernameInput.setText("Kunnaal")
        passwordInput.setText(value)
    }
}