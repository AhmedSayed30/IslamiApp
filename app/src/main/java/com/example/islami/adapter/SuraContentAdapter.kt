package com.example.islami.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.R

class SuraContentAdapter(var suraLines: List<String?>?) :RecyclerView.Adapter<SuraContentAdapter.SuraViewHolder>(){

    class SuraViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val suraLineText : TextView = itemView.findViewById(R.id.sura_line_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sura_line,parent,false)
        return SuraViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuraViewHolder, position: Int) {
        holder.suraLineText.text=suraLines?.get(position)
    }

    override fun getItemCount(): Int {
        return suraLines?.size ?: 0
    }

    fun updata(suraLines: List<String>){
        this.suraLines=suraLines
        notifyDataSetChanged()
    }

}