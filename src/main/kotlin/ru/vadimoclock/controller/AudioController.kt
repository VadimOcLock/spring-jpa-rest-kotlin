package ru.vadimoclock.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import ru.vadimoclock.dto.*
import ru.vadimoclock.service.AudioService

@RestController
@RequestMapping("/audio")
class AudioController(
    private val audioService: AudioService,
) {
    @PostMapping("/cut")
    fun cutWavFile(@RequestBody dto: CutFileDto) =
        audioService.cutFile(dto)


    @PostMapping("/editVolume")
    fun editVolume(@RequestBody dto: VolumeEditFileDto) =
        audioService.editVolumeFile(dto)


    @PostMapping("/volumeEdit")
    fun editTempo(@RequestBody dto: TempoEditFileDto) =
        audioService.editTempoFile(dto)


    @PostMapping("/mesh")
    fun mesh(@RequestBody dto: MeshFilesDto) =
        audioService.mesh(dto)


    @PostMapping("/union")
    fun union(@RequestBody dto: UnionFilesDto) =
        audioService.union(dto)


    @GetMapping("/test")
    fun test() = "It works"


}