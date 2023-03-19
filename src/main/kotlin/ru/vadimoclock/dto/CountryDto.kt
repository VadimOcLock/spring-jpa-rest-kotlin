package ru.vadimoclock.dto

data class CountryDto(
    val id: Int? = null,
    val name: String,
    val population: Int,
    val cities: List<CityDto>
)