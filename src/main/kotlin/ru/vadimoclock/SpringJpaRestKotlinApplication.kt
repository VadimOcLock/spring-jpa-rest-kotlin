package ru.vadimoclock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringJpaRestKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringJpaRestKotlinApplication>(*args)
}
