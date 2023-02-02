package com.example.islami.activity.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.islami.R
import com.example.islami.databinding.ActivityMainBinding
import com.example.islami.fragments.HadethFragment
import com.example.islami.fragments.QuranFragment
import com.example.islami.fragments.RadioFragment
import com.example.islami.fragments.SebhaFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.quran->showFragment(QuranFragment())
                R.id.hadeth->showFragment(HadethFragment())
                R.id.sebha->showFragment(SebhaFragment())
                R.id.radio->showFragment(RadioFragment())
            }
            return@setOnItemSelectedListener true
        }
        binding.btnNavigationView.selectedItemId = R.id.quran
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
    }

}