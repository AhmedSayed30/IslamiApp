package com.example.islami.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.R
import com.example.islami.callbacks.OnSuraClickListener

class SurasNameAdapter(private var items: Array<String>)
    : RecyclerView.Adapter<surasNameViewHolder>() {
    var onSuraClickListener : OnSuraClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): surasNameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quran_item_name,parent,false)
        return surasNameViewHolder(view)
    }

    override fun onBindViewHolder(holder: surasNameViewHolder, position: Int) {
        val item = items[position]
        holder.suraName.text = items.get(position)
        holder.suraContentCounter.text = "286"
        holder.itemView.setOnClickListener{
            onSuraClickListener?.onSuraClick(position,items.get(position))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

open class surasNameViewHolder (view: View):RecyclerView.ViewHolder(view) {
    val suraName : TextView = itemView.findViewById(R.id.tv_sura_name)
    val suraContentCounter : TextView = itemView.findViewById(R.id.tv_sura_contant_count)
}
