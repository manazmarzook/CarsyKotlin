package com.example.carsykotlin.ui.fragments.timeline.adapter

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.carsykotlin.data.CostListItem
import com.example.carsykotlin.databinding.GeneralItemTimelineRecyclerviewBinding
import com.example.carsykotlin.util.dateFormatter

class GeneralItemViewHolder(private val binding: GeneralItemTimelineRecyclerviewBinding)
    : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(item: CostListItem.CostGeneralItem, context: Context) {
        binding.apply {
            timeLineCostTypeNameTextView.text = item.cost.type.costType
            timeLineFullDateTextView.text = item.cost.date.format(dateFormatter)
            timeLineCostAmountTextView.text = "${item.cost.amount} zł"
            iconTimeLineImageView.background =
                ContextCompat.getDrawable(context, item.cost.type.icon)
        }
    }
}