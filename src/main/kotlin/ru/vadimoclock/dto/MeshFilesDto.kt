package ru.vadimoclock.dto

import org.springframework.web.multipart.MultipartFile

data class MeshFilesDto (
        val files: Array<MultipartFile>?
)
