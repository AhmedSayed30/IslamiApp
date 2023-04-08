package com.example.islami.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.R
import com.example.islami.databinding.HadethItemNameBinding
import com.example.islami.databinding.ItemHadethLineBinding

class HadethContentAdabter(var item : String?)
    : RecyclerView.Adapter<HadethContentAdabter.ViexHolder>() {
    class ViexHolder(val viewBinding  : ItemHadethLineBinding)
        :RecyclerView.ViewHolder(viewBinding.root){
        val hadethLineText : TextView = itemView.findViewById(R.id.hadeth_line_text)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViexHolder {
        val viewBinding = ItemHadethLineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return ViexHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViexHolder, position: Int) {
        holder.viewBinding.hadethLineText.text = item?.get(position).toString()
    }

    override fun getItemCount(): Int {
        return 0
    }
    fun updata(hadethLines: String?){
        this.item=hadethLines
        notifyDataSetChanged()
    }

}