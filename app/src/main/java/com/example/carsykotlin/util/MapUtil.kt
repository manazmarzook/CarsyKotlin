package com.example.carsykotlin.util

fun <K, V> Map<K, V>.getKeyByValue(targetValue: V): K? {
    return this.filterValues { it == targetValue }
        .keys
        .firstOrNull()
}