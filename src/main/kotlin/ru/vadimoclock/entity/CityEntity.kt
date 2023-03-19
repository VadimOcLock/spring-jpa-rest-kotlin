package ru.vadimoclock.entity

import jakarta.persistence.*

@Entity
@Table(name = "City")
class CityEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val name: String = "",
    @ManyToOne
    @JoinColumn(name = "country_id")
    var country: CountryEntity,
) {

}