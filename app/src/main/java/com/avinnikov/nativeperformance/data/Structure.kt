package com.avinnikov.nativeperformance.data

data class Structure(
    val number: Double,
    val string: String
) : Comparable<Structure> {
    override fun compareTo(other: Structure) = compareValuesBy(this, other,
        { it.number }
    )
}