package com.example.learn_dark_mode_kotlin

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import com.example.learn_dark_mode_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    private var isNightMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE)
        isNightMode = sharedPreferences.getBoolean("night", false)

        // Apply initial theme based on saved preference
        applyTheme(isNightMode)

        binding.switcher.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        isNightMode = !isNightMode
        applyTheme(isNightMode)
        sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putBoolean("night", isNightMode)
        sharedPreferencesEditor.apply()
    }

    private fun applyTheme(isNightMode: Boolean) {
        when (isNightMode) {
            true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        delegate.applyDayNight() // Important: Apply the theme change
    }

}