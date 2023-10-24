package com.example.carsykotlin.ui.fragments.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carsykotlin.R
import com.example.carsykotlin.data.CostType
import com.example.carsykotlin.data.DataProvider
import com.example.carsykotlin.databinding.FragmentOverviewBinding
import com.example.carsykotlin.databinding.FragmentTimeLineBinding
import com.example.carsykotlin.ui.fragments.overview.adapters.OverviewCarsAdapter
import com.example.carsykotlin.ui.fragments.overview.adapters.OverviewCostsAdapter
import com.example.carsykotlin.util.decimalFormat

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCostsUi()
        setupGarageUi()
        setupTotalCostsUi()
    }

    private fun setupCostsUi() {
        binding.overviewCostsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = OverviewCostsAdapter()
        }
    }

    private fun setupGarageUi() {
        binding.overviewCarsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = OverviewCarsAdapter()
        }
    }

    private fun setupTotalCostsUi() {
        binding.apply {
            overviewFueling.text = totalValue(CostType.REFUELING)
            overviewService.text = totalValue(CostType.SERVICE)
            overviewParking.text = totalValue(CostType.PARKING)
            overviewInsurance.text = totalValue(CostType.INSURANCE)
            overviewTicket.text = totalValue(CostType.TICKET)
        }
    }

    private fun totalValue(costType: CostType): String {
        return decimalFormat.format(
            DataProvider.cars
            .flatMap { it.costs }
            .filter { it.type == costType }
            .sumOf { it.amount }).toString()
    }
}
