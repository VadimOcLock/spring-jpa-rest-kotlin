package ru.vadimoclock.dto

import org.springframework.web.multipart.MultipartFile

data class VolumeEditFileDto(
        val volume: String,
        val file: MultipartFile?
)