package com.example.islami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.islami.adapter.Constants
import com.example.islami.adapter.SuraContentAdapter
import com.example.islami.databinding.ActivitySuraDetailsBinding

class SuraDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuraDetailsBinding
    var suraPosition : Int? = null
    var suraName : String? =null
    lateinit var adapter : SuraContentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuraDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = SuraContentAdapter(null)
        suraName = intent.getStringExtra(Constants.EXTRA_SURA_NAME_KEY)
        suraPosition = intent.getIntExtra(Constants.EXTRA_SURA_POSITION,-1)
        binding.suraContentRecyclerView.adapter = adapter
        readFileText()
        binding.backArrow.setOnClickListener{
            finish()
        }
        binding.suraName.text = suraName
    }
    fun readFileText() {
        val fileName = "${suraPosition?.plus(1)}.txt"
        val fileContent = assets.open(fileName).bufferedReader().use { it.readText() }
        adapter.updata(fileContent.split("\n"))
    }
}