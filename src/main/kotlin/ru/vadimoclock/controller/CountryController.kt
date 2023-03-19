package ru.vadimoclock.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.vadimoclock.dto.CountryDto
import ru.vadimoclock.service.CountryService

@RestController
@RequestMapping("/countries")
@Tag(name = "Country controller", description = "Information about some countries, their population and cities")
class CountryController(
    private val countryService: CountryService,
) {

    @GetMapping
    @Operation(summary = "Pagination of countries by 2 elements")
    fun getAll(
        @Parameter(description = "Page index, starts from 0")
        @RequestParam("page") pageIndex: Int
    ): List<CountryDto> =
        countryService.getAll(pageIndex)

    @GetMapping("/{id}")
    @Operation(summary = "Get country by id")
    fun getById(
        @Parameter(description = "Country id")
        @PathVariable("id") id: Int
    ): CountryDto =
        countryService.getById(id)

    @GetMapping("/search")
    @Operation(summary = "Search countries by name fragment")
    fun searchCountries(
        @Parameter(description = "Name fragment, ignore case")
        @RequestParam("prefix") prefix: String
    ): List<CountryDto> =
        countryService.search(prefix)

    @GetMapping("/names")
    @Operation(summary = "Get all country names")
    fun getCountryNames(): List<String> =
        countryService.getCountryNames()

    @PostMapping
    @Operation(summary = "Create new country")
    fun create(@RequestBody dto: CountryDto): Int =
        countryService.create(dto)

    @PutMapping("/{id}")
    @Operation(summary = "Update country")
    fun update(
        @Parameter(description = "Country id")
        @PathVariable id: Int,
        @RequestBody dto: CountryDto
    ) {
        countryService.update(id, dto)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete country")
    fun delete(
        @Parameter(description = "Country id")
        @PathVariable id: Int
    ) {
        countryService.delete(id)
    }

}