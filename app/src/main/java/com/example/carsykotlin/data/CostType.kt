package com.example.carsykotlin.data

import com.example.carsykotlin.R

enum class CostType(val costType: String, val icon: Int) {
    REFUELING("Tankowanie", R.drawable.ic_fuel),
    SERVICE("Serwis", R.drawable.ic_car_repair),
    PARKING("Parking", R.drawable.ic_parking),
    INSURANCE("Ubezpieczenie", R.drawable.ic_general_cost),
    TICKET("Mandat", R.drawable.ic_ticket)
}