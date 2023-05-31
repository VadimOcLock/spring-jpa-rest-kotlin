package ru.vadimoclock.responses

data class FileResponse (
        val file: ByteArray,
        val statusCode: Int,
        val description: String
)