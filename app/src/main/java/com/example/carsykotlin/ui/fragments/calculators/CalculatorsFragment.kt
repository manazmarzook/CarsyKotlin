package com.example.carsykotlin.ui.fragments.calculators

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.carsykotlin.R
import com.example.carsykotlin.databinding.FragmentCalculatorsBinding
import com.example.carsykotlin.util.decimalFormat
import com.example.carsykotlin.util.getKeyByValue

class CalculatorsFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorsBinding
    private val calculators = mapOf(
        0 to "Koszt podróży",
        1 to "Odległość",
        2 to "Wymagane paliwo"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorsBinding.inflate(inflater)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupDropDownSelector()
    }

    private fun setupDropDownSelector() {
        binding.autoCompleteTextView.apply {

            setDropDownBackgroundResource(R.color.dark_blue_900)

            setText(calculators[0])
            binding.textViewMainTitle.text = calculators[0]

            setAdapter(ArrayAdapter(requireContext(), R.layout.dropdown_item, calculators.values.toList()))
            onItemClickListener = autoCompleteTextViewOnItemClickListener()
        }
    }

    private fun autoCompleteTextViewOnItemClickListener() =
        AdapterView.OnItemClickListener { _, _, position, _ ->
            binding.apply {
                textViewMainTitle.text = calculators.getValue(position)
                editInputLayout1.hint = if (position == calculators.getKeyByValue("Odległość")) "Paliwo [l]" else "Odległość [km]"
                setupCalculateButtonOnClickListener(position)
            }
        }

    private fun FragmentCalculatorsBinding.setupCalculateButtonOnClickListener(
        position: Int
    ) {
        calculateButton.setOnClickListener {
            val input = getEditTextsData()

            if (checkForEmptyEditTexts(input)) {
                val values: Pair<String, String> = calculators(position, input)
                textViewMainValue.text = values.first
                textViewBottomValue.text = values.second
            }
        }
    }

    private fun checkForEmptyEditTexts(input: Triple<String, String, String>) =
        (input.first.isNotEmpty()
                && input.second.isNotEmpty()
                && input.third.isNotEmpty())

    private fun getEditTextsData() = Triple(
        binding.editText1.text.toString(),
        binding.editText2.text.toString(),
        binding.editText3.text.toString()
    )

    private fun calculators(
        position: Int,
        values: Triple<String, String, String>
    ): Pair<String, String> {
        return when (position) {
            0 -> { travelCalculator(values) }
            1 -> { distanceCalculator(values) }
            2 -> { fuelCalculator(values) }
            else -> Pair("", "")
        }
    }

    private fun fuelCalculator(values: Triple<String, String, String>): Pair<String, String> {

        val distance = values.first.toDouble()
        val cost = values.second.toDouble()
        val fuelUsage = values.third.toDouble()

        val fuelCost = (distance * fuelUsage) / 100
        val fuelAmount = fuelCost * cost

        val stringMain = decimalFormat.format(fuelCost).toString()
        val stringBottom = decimalFormat.format(fuelAmount).toString()

        return Pair("$stringMain l", "$stringBottom zł")
    }

    private fun distanceCalculator(values: Triple<String, String, String>): Pair<String, String> {

        val fuel = values.first.toDouble()
        val price = values.second.toDouble()
        val fuelUsage = values.third.toDouble()

        val distance = fuel * price
        val totalCost = (100 * fuel) / fuelUsage

        val stringBottom = decimalFormat.format(distance).toString()
        val stringMain = decimalFormat.format(totalCost).toString()

        return Pair("$stringMain km", "$stringBottom zł")
    }

    private fun travelCalculator(values: Triple<String, String, String>): Pair<String, String> {

        val distance = values.first.toDouble()
        val price = values.second.toDouble()
        val fuelUsage = values.third.toDouble()

        val totalFuel = (fuelUsage / 100) * distance
        val totalPrice = totalFuel * price

        val stringBottom = decimalFormat.format(totalFuel).toString()
        val stringMain = decimalFormat.format(totalPrice).toString()

        return Pair("$stringMain zł", "$stringBottom l")
    }




}