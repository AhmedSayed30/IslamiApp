package com.example.islami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.islami.adapter.Constants
import com.example.islami.adapter.Hadeth
import com.example.islami.adapter.HadethContentAdabter
import com.example.islami.adapter.SuraContentAdapter
import com.example.islami.databinding.ActivityHadethDetailsBinding


class HadethDetailsActivity : AppCompatActivity() {

    private val TAG = "HadethDetailsActivity"

    private lateinit var binding: ActivityHadethDetailsBinding
    var hadethName: String? = null
    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHadethDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hadethName = intent.getStringExtra(Constants.EXTRA_HADETH_NAME_KEY)
        Log.i(TAG,intent.getParcelableArrayExtra("content", Hadeth::class.java).toString())
        //binding.hadethContentRecyclerView.adapter = HadethContentAdabter(allAhadeth)
        binding.backArrow.setOnClickListener {
            finish()
        }
        binding.hadethName.text = hadethName
    }

}