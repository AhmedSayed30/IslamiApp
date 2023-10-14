package com.example.islami.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.HadethDetailsActivity
import com.example.islami.R
import com.example.islami.SuraDetailsActivity
import com.example.islami.adapter.Constants
import com.example.islami.adapter.Hadeth
import com.example.islami.adapter.HadthNameAdapter
import com.example.islami.callbacks.OnHadethClickListener
import com.example.islami.callbacks.OnSuraClickListener
import com.example.islami.databinding.ActivityMainBinding
import com.example.islami.databinding.FragmentHadethBinding

class HadethFragment : Fragment() {
    private lateinit var binding: FragmentHadethBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHadethBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readhadethFile()
        val adapter = HadthNameAdapter(allAhadeth)
        binding.hadethRecyclerView.adapter = adapter
        adapter.onHadethClickListener = object : OnHadethClickListener {
            override fun onHadethClick(hadeth: Hadeth) {
                val intent = Intent(requireActivity(), HadethDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_HADETH_NAME_KEY,hadeth.title)
                intent.putExtra("content",hadeth.content)
                startActivity(intent)
            }
        }
    }

    val allAhadeth = ArrayList<Hadeth>()
    private fun readhadethFile() {
        val fileContent = activity?.assets?.open("ahadeth.txt")?.bufferedReader().use { it?.readText() }
        if(fileContent == null)return;
        val ahadethContent = fileContent.trim().split("#")
        ahadethContent.forEach{
            val indexOfFirstLine = it.indexOf('\n')
            val title = it.trim().substringBefore('\n')
            val content = it.trim().substringAfter('\n')
            allAhadeth.add(Hadeth(title,content))
        }
    }

}