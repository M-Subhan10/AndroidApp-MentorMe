package com.example.mentorme

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase

class Register_Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        // UI Elements
        val nameInput = findViewById<EditText>(R.id.input_name)
        val emailInput = findViewById<EditText>(R.id.input_email)
        val numberInput = findViewById<EditText>(R.id.input_number)
        val countryInput = findViewById<AutoCompleteTextView>(R.id.input_country)
        val cityInput = findViewById<EditText>(R.id.input_city)
        val passwordInput = findViewById<EditText>(R.id.input_password)
        val registerButton = findViewById<Button>(R.id.register_btn)

        // Country dropdown list
        val countries = listOf(
            "Pakistan", "India", "United States", "United Kingdom", "Canada", "Australia",
            "Germany", "France", "Italy", "China", "Japan", "Russia", "Brazil", "South Africa"
        )

        val countryAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countries)
        countryInput.setAdapter(countryAdapter)
        countryInput.setOnClickListener { countryInput.showDropDown() }

        // Navigate to Login
        findViewById<TextView>(R.id.signin_text).setOnClickListener {
            startActivity(Intent(this, Login_Activity::class.java))
        }

        // Register Button Listener
        registerButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val number = numberInput.text.toString().trim()
            val country = countryInput.text.toString().trim()
            val city = cityInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || number.isEmpty() ||
                country.isEmpty() || city.isEmpty() || password.isEmpty()
            ) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 8) {
                Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser

                        // Update display name
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()

                        user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                            if (updateTask.isSuccessful) {
                                // Save user details to Realtime Database
                                val uid = user.uid
                                val userMap = hashMapOf(
                                    "name" to name,
                                    "email" to email,
                                    "number" to number,
                                    "country" to country,
                                    "city" to city
                                )

                                FirebaseDatabase.getInstance().reference
                                    .child("Users")
                                    .child(uid)
                                    .setValue(userMap)
                                    .addOnCompleteListener { dbTask ->
                                        if (dbTask.isSuccessful) {
                                            Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show()
                                            startActivity(Intent(this, Login_Activity::class.java))
                                            finish()
                                        } else {
                                            Toast.makeText(this, "Database Error: ${dbTask.exception?.message}", Toast.LENGTH_LONG).show()
                                        }
                                    }
                            } else {
                                Toast.makeText(this, "Profile update failed: ${updateTask.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
