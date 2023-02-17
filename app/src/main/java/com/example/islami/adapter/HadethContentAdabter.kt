package com.example.islami.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.databinding.HadethItemNameBinding
import com.example.islami.databinding.ItemHadethLineBinding

class HadethContentAdabter(val item : ArrayList<Hadeth>)
    : RecyclerView.Adapter<HadethContentAdabter.ViexHolder>() {
    class ViexHolder(val viewBinding  : ItemHadethLineBinding)
        :RecyclerView.ViewHolder(viewBinding.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViexHolder {
        val viewBinding = ItemHadethLineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return ViexHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViexHolder, position: Int) {
        holder.viewBinding.hadethLineText.text = item[position].content
    }

    override fun getItemCount(): Int {
        return item.size
    }

}