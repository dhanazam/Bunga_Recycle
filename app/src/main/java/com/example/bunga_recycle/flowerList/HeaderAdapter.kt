package com.example.bunga_recycle.flowerList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bunga_recycle.R

class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var flowerCount: Int = 0

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder (view) {
        private val flowerNumberTextView: TextView = itemView.findViewById(R.id.flower_number_text)

        fun bind(flowerCount: Int) {
            flowerNumberTextView.text = flowerCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)

        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(flowerCount)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun updateFlowerCount(updatedFlowerCount: Int) {
        flowerCount = updatedFlowerCount
    }
}