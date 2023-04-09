package com.example.islami.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.R
import com.example.islami.databinding.FragmentSebhaBinding

class SebhaFragment : Fragment() {
    private lateinit var binding : FragmentSebhaBinding
    var counter :Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSebhaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvCounter.text = "$counter"
        binding.ivSebha.setOnClickListener{
            onSebhaClicked()
        }
    }

    private fun onSebhaClicked() {
        binding.ivSebha.rotation += 5
        counter++
        binding.tvCounter.text = "$counter"

        if (counter == 33){
            if (binding.tathbih.text == "لا اله الا الله، وحده لا شريك له، له الملك، وله الحمد، وهو علي كل شئ قدير"){
                binding.tathbih.text = "سبحان الله"
                counter = 0
                binding.tvCounter.text = "$counter"
            }else if (binding.tathbih.text == "سبحان الله"){
                binding.tathbih.text = "الحمد لله"
                counter = 0
                binding.tvCounter.text = "$counter"
            }else if (binding.tathbih.text == "الحمد لله"){
                binding.tathbih.text = "الله اكبر"
                counter = 0
                binding.tvCounter.text = "$counter"
            }else if (binding.tathbih.text == "الله اكبر"){
                binding.tathbih.text = "لا اله الا الله، وحده لا شريك له، له الملك، وله الحمد، وهو علي كل شئ قدير"
                counter = 32
                binding.tvCounter.text = "0"
            }
        }
    }
}