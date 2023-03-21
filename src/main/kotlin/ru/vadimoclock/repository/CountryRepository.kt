package ru.vadimoclock.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.CrudRepository
import ru.vadimoclock.entity.CountryEntity
import ru.vadimoclock.model.NameOnly

interface CountryRepository: CrudRepository<CountryEntity, Int> {

    fun findByOrderByPopulation(pageable: Pageable): List<CountryEntity>

    fun findByNameStartingWithIgnoreCaseOrderByName(prefix: String): List<CountryEntity>

    fun findAllByOrderByName(): List<NameOnly>

    @Query(value = "call get_count_of_countries();", nativeQuery = true)
    fun getCountOfCountries(): Int
}