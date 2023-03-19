package ru.vadimoclock.service.impl

import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.vadimoclock.dto.CityDto
import ru.vadimoclock.dto.CountryDto
import ru.vadimoclock.entity.CityEntity
import ru.vadimoclock.entity.CountryEntity
import ru.vadimoclock.exception.CountryNotFoundException
import ru.vadimoclock.repository.CityRepository
import ru.vadimoclock.repository.CountryRepository
import ru.vadimoclock.service.CountryService

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository,
    private val cityRepository: CityRepository,
) : CountryService {

    override fun getAll(pageIndex: Int): List<CountryDto> {
        return countryRepository.findByOrderByPopulation(PageRequest.of(pageIndex, 2))
            .map { it.toDto() }
    }

    override fun getById(id: Int): CountryDto {
        return countryRepository.findByIdOrNull(id)
            ?.toDto()
            ?: throw CountryNotFoundException(id)
    }

    override fun search(prefix: String): List<CountryDto> =
        countryRepository.findByNameStartingWithIgnoreCaseOrderByName(prefix)
            .map { it.toDto() }

    @Transactional
    override fun create(dto: CountryDto): Int {
        val countryEntity = countryRepository.save(dto.toEntity())
        val cities = dto.cities.map { it.toEntity(countryEntity) }
        cityRepository.saveAll(cities)

        return countryEntity.id
    }

    @Transactional
    override fun update(id: Int, dto: CountryDto) {
        var existingCountry = countryRepository.findByIdOrNull(id)
            ?: throw CountryNotFoundException(id)

        existingCountry.name = dto.name
        existingCountry.population = dto.population

        existingCountry = countryRepository.save(existingCountry)

        val cities = dto.cities.map { it.toEntity(existingCountry) }

        cityRepository.deleteAllByCountry(existingCountry)
        cityRepository.saveAll(cities)
    }

    override fun delete(id: Int) {
        val existingCountry = countryRepository.findByIdOrNull(id)
            ?: throw CountryNotFoundException(id)

        countryRepository.deleteById(existingCountry.id)
    }

    private fun CountryEntity.toDto(): CountryDto =
        CountryDto(
            id = this.id,
            name = this.name,
            population = this.population,
            cities = this.cities.map { it.toDto() }
        )

    private fun CityEntity.toDto(): CityDto =
        CityDto(
            name = this.name
        )

    private fun CountryDto.toEntity(): CountryEntity =
        CountryEntity(
            id = 0,
            name = this.name,
            population = this.population
        )

    private fun CityDto.toEntity(country: CountryEntity): CityEntity =
        CityEntity(
            id = 0,
            name = this.name,
            country = country
        )

}