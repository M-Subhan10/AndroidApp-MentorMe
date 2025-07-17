package com.example.mentorme

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Login_Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.input_email)
        passwordEditText = findViewById(R.id.input_password)

        // Sign Up Navigation
        findViewById<TextView>(R.id.signup_text).setOnClickListener {
            startActivity(Intent(this, Register_Activity::class.java))
        }

        // Forgot Password
        findViewById<TextView>(R.id.password_forgot).setOnClickListener {
            startActivity(Intent(this, ForgotPassword_Activity::class.java))
        }

        // Login Logic
        findViewById<TextView>(R.id.login_btn).setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(email, password)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login successful
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainMenuScreen_activity::class.java))
                    finish()
                } else {
                    // Login failed
                    Toast.makeText(this, "Email or Password is incorrect", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
