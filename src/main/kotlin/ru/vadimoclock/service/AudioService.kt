package ru.vadimoclock.service

import ru.vadimoclock.dto.*
import ru.vadimoclock.responses.FileResponse

interface AudioService {

    fun cutFile(dto: CutFileDto): FileResponse
    fun editVolumeFile(dto: VolumeEditFileDto): FileResponse
    fun editTempoFile(dto: TempoEditFileDto): FileResponse
    fun mesh(dto: MeshFilesDto): FileResponse
    fun union(dto: UnionFilesDto): FileResponse
}