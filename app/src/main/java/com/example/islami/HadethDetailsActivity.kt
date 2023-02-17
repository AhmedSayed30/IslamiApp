package com.example.islami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.islami.adapter.Constants
import com.example.islami.adapter.HadethContentAdabter
import com.example.islami.adapter.SuraContentAdapter
import com.example.islami.databinding.ActivityHadethDetailsBinding


class HadethDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHadethDetailsBinding
    var hadethName : String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHadethDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hadethName = intent.getStringExtra(Constants.EXTRA_HADETH_NAME_KEY)
        //binding.hadethContentRecyclerView.adapter = HadethContentAdabter(allAhadeth)
        binding.backArrow.setOnClickListener{
            finish()
        }
        binding.hadethName.text = hadethName
    }

}