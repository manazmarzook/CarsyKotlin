package com.example.carsykotlin.ui.fragments.overview.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.carsykotlin.data.Car
import com.example.carsykotlin.data.CostType
import com.example.carsykotlin.databinding.CostOverviewItemRecyclerviewBinding
import com.example.carsykotlin.util.decimalFormat

class OverviewCostsViewHolder(private val binding: CostOverviewItemRecyclerviewBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind (item: Car){
        binding.apply {
            overviewRecyclerViewCarName.text = item.name
            overviewRecyclerViewFueling.text = costValue(item, CostType.REFUELING)
            overviewRecyclerViewService.text = costValue(item, CostType.SERVICE)
            overviewRecyclerViewParking.text = costValue(item, CostType.PARKING)
            overviewRecyclerViewInsurance.text = costValue(item, CostType.INSURANCE)
            overviewRecyclerViewTicket.text = costValue(item, CostType.TICKET)
        }
    }

    private fun costValue(item: Car, costType: CostType) =
        decimalFormat.format(item.costs
            .filter { it.type == costType }
            .sumOf { it.amount }).toString()
}