package com.example.islami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.islami.adapter.Constants
import com.example.islami.databinding.ActivityHadethDetailsBinding

class HadethDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHadethDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHadethDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvHadethContent.text=intent.getStringExtra("content")
        binding.hadethName.text = intent.getStringExtra(Constants.EXTRA_HADETH_NAME_KEY)
        binding.backArrow.setOnClickListener {
            finish()
        }
    }

}