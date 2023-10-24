package com.example.carsykotlin.ui.fragments.timeline.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.carsykotlin.data.CostListItem
import com.example.carsykotlin.databinding.MonthItemTimelineRecyclerviewBinding

class MonthItemViewHolder(private val binding: MonthItemTimelineRecyclerviewBinding)
    : RecyclerView.ViewHolder(binding.root)  {

        fun bind(item: CostListItem.CostMonthItem){
            binding.timeLineMonthTextView.text = item.month
        }
}