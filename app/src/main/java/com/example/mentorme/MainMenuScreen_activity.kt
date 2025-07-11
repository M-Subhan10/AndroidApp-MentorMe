package com.example.mentorme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.mentorme.databinding.ActivityMainMenuScreenBinding

class MainMenuScreen_activity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle insets (safe area)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set default fragment (Home)
        replaceFragment(HomeFragment())

        // Handle bottom nav clicks
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.search_menu -> {
                    replaceFragment(SearchFragment())
                    true
                }
                R.id.chat_menu -> {
                    replaceFragment(ChatFragment())
                    true
                }
                R.id.profile_menu -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Floating button action (optional)
        binding.fab.setOnClickListener {
            // For example: Open AddFragment or a Toast
            // replaceFragment(AddFragment())
        }
    }

    // Replaces the frame layout content with the passed fragment
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
