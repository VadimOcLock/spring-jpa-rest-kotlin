package ru.vadimoclock.repository

import org.springframework.data.repository.CrudRepository
import ru.vadimoclock.entity.CityEntity
import ru.vadimoclock.entity.CountryEntity

interface CityRepository: CrudRepository<CityEntity, Int> {

    fun deleteAllByCountry(country: CountryEntity)
}