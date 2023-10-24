package com.example.carsykotlin.ui.fragments.timeline.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.carsykotlin.data.CostListItem
import com.example.carsykotlin.databinding.YearItemTimelineRecyclerviewBinding

class YearItemViewHolder(private val binding: YearItemTimelineRecyclerviewBinding)
    : RecyclerView.ViewHolder(binding.root)  {

    fun bind(item: CostListItem.CostYearItem){
        binding.timeLineYearTextView.text = item.year
    }
}