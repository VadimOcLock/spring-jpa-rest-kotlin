package ru.vadimoclock.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import ru.vadimoclock.entity.CountryEntity
import ru.vadimoclock.model.NameOnly

interface CountryRepository: CrudRepository<CountryEntity, Int> {

    fun findByOrderByPopulation(pageable: Pageable): List<CountryEntity>

    fun findByNameStartingWithIgnoreCaseOrderByName(prefix: String): List<CountryEntity>

    fun findAllByOrderByName(): List<NameOnly>
}