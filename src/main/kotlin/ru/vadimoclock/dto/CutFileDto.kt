package ru.vadimoclock.dto

import org.springframework.web.multipart.MultipartFile

data class CutFileDto(
        val startTime: String?,
        val endTime: String?,
        val file: MultipartFile?
)