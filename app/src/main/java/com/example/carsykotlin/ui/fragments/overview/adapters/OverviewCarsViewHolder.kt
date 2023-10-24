package com.example.carsykotlin.ui.fragments.overview.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.carsykotlin.data.Car
import com.example.carsykotlin.databinding.CarsOverviewItemRecyclerviewBinding
import com.example.carsykotlin.util.decimalFormat

class OverviewCarsViewHolder(private val binding: CarsOverviewItemRecyclerviewBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind (item: Car){
        binding.apply {
            overviewViewPagerCarName.text = item.name
            overviewViewPagerBrand.text = item.brand
            overviewViewPagerModel.text = item.model
            overviewViewPagerYearOfProduction.text = item.yearOfProduction.toString()
            overviewViewPagerTotalCosts.text = decimalFormat.format(item.costs.sumOf { it.amount }).toString()
        }
    }
}