package com.example.islami.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami.R
import com.example.islami.callbacks.OnHadethClickListener
import com.example.islami.callbacks.OnSuraClickListener
import com.example.islami.databinding.HadethItemNameBinding

class HadthNameAdapter(val item : List<Hadeth>)
    : RecyclerView.Adapter<HadthNameAdapter.ViewHolder>() {
        var onHadethClickListener : OnHadethClickListener?=null

    class ViewHolder(val viewBinding : HadethItemNameBinding)
        : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = HadethItemNameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding.tvHadethName.text = item[position].title
        if (holder.adapterPosition == item.size-1){
            holder.itemView.findViewById<View>(R.id.border_bottom).
                    visibility = View.GONE
        }
        holder.itemView.setOnClickListener{
            onHadethClickListener?.onHadethClick(item[position])
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}