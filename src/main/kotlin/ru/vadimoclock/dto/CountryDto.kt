package ru.vadimoclock.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

@Schema(description = "Country information")
data class CountryDto(

    @Schema(description = "Country id")
    val id: Int? = null,

    @Schema(description = "Country name")
    @Size(min = 1, max = 100)
    val name: String,

    @Schema(description = "Country population")
    @Min(1)
    val population: Int,

    @Schema(description = "Country cities")
    val cities: List<CityDto>
)