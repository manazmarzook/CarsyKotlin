package com.example.carsykotlin.ui.fragments.overview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carsykotlin.data.DataProvider
import com.example.carsykotlin.databinding.CostOverviewItemRecyclerviewBinding

class OverviewCostsAdapter : RecyclerView.Adapter<OverviewCostsViewHolder>() {

    private val cars = DataProvider.cars

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        OverviewCostsViewHolder(
            CostOverviewItemRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: OverviewCostsViewHolder, position: Int) {
        val item = cars[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = cars.size
}