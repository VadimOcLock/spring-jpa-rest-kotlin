package ru.vadimoclock.service

import ru.vadimoclock.dto.*
import ru.vadimoclock.model.CutRequestModel
import ru.vadimoclock.responses.FileResponse
import java.io.File

interface AudioService {

    fun cutFile(dto: CutRequestModel): File
    fun editVolumeFile(dto: VolumeEditFileDto): FileResponse
    fun editTempoFile(dto: TempoEditFileDto): FileResponse
    fun mesh(dto: MeshFilesDto): FileResponse
    fun union(dto: UnionFilesDto): FileResponse
}