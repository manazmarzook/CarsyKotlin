package com.example.carsykotlin.ui.fragments.timeline

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carsykotlin.R
import com.example.carsykotlin.data.DataProvider
import com.example.carsykotlin.databinding.FragmentTimeLineBinding
import com.example.carsykotlin.ui.fragments.timeline.adapter.TimeLineAdapter

class TimeLineFragment : Fragment() {

    private lateinit var binding: FragmentTimeLineBinding
    private val recycler by lazy { setupRecyclerView() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimeLineBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupAutoCompleteAdapter(binding.autoCompleteTextView)

        setupDropDownSelector(recycler)
    }

    private fun setupDropDownSelector(recycler: RecyclerView) {
        binding.autoCompleteTextView.apply {
            setDropDownBackgroundResource(R.color.dark_blue_900)
            if (text.toString().isEmpty())
                setText(DataProvider.cars[0].name)

            val itemPosition = DataProvider.cars.indexOfFirst { it.name == binding.autoCompleteTextView.text.toString() }
            setCurrentAdapter(recycler, itemPosition)

            setupAutoCompleteAdapter(this)

            onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    setCurrentAdapter(recycler, position)
                }
        }
    }

    private fun setCurrentAdapter(
        recycler: RecyclerView,
        position: Int
    ) {
        recycler.swapAdapter(
            TimeLineAdapter(
                DataProvider.cars[position].costs,
                requireContext()
            ), true
        )
    }

    private fun setupAutoCompleteAdapter(autoCompleteTextView: AutoCompleteTextView) {
        autoCompleteTextView.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.dropdown_item,
                DataProvider.cars.map { it.name })
        )
    }

    private fun setupRecyclerView(): RecyclerView {
        return binding.timeLineRecyclerView.apply {
            adapter = TimeLineAdapter(DataProvider.cars[0].costs, requireContext())
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}