package ru.vadimoclock.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(description = "City information")
data class CityDto(

    @Schema(description = "City name")
    @NotBlank
    @Size(min = 1, max = 100)
    val name: String,
)