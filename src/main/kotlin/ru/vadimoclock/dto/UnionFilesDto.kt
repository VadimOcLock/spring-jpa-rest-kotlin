package ru.vadimoclock.dto

import org.springframework.web.multipart.MultipartFile

data class UnionFilesDto(
        val files: Array<MultipartFile>?
)