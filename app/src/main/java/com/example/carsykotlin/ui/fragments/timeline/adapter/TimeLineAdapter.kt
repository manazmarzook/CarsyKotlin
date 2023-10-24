package com.example.carsykotlin.ui.fragments.timeline.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carsykotlin.data.Cost
import com.example.carsykotlin.data.CostListItem
import com.example.carsykotlin.data.DataProvider
import com.example.carsykotlin.databinding.GeneralItemTimelineRecyclerviewBinding
import com.example.carsykotlin.databinding.MonthItemTimelineRecyclerviewBinding
import com.example.carsykotlin.databinding.YearItemTimelineRecyclerviewBinding

class TimeLineAdapter(costsList: List<Cost>, private val context: Context)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val itemList = DataProvider.getTimeLineList(costsList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CostListItem.TYPE_YEAR -> createYearItemViewHolder(parent)
            CostListItem.TYPE_MONTH -> createMonthItemViewHolder(parent)
            else -> createGeneralItemViewHolder(parent)
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when(holder){
            is YearItemViewHolder -> holder.bind(itemList[position] as CostListItem.CostYearItem)
            is MonthItemViewHolder -> holder.bind(itemList[position] as CostListItem.CostMonthItem)
            is GeneralItemViewHolder -> holder.bind(itemList[position] as CostListItem.CostGeneralItem, context)
            else -> throw IllegalArgumentException("wrong ViewHolder")
        }

    override fun getItemViewType(position: Int) = itemList[position].viewType

    private fun createYearItemViewHolder(parent: ViewGroup) =
        YearItemViewHolder(
            YearItemTimelineRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    private fun createMonthItemViewHolder(parent: ViewGroup) =
        MonthItemViewHolder(
            MonthItemTimelineRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    private fun createGeneralItemViewHolder(parent: ViewGroup) =
        GeneralItemViewHolder(
            GeneralItemTimelineRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
}