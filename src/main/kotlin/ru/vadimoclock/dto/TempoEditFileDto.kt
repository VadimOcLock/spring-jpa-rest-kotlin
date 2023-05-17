package ru.vadimoclock.dto

import org.springframework.web.multipart.MultipartFile

data class TempoEditFileDto(
        val tempo: String?,
        val file: MultipartFile?
)
