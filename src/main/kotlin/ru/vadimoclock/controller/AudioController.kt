package ru.vadimoclock.controller

import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.jetbrains.annotations.NotNull
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpHeaders
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.vadimoclock.dto.*
import ru.vadimoclock.model.CutRequestModel
import ru.vadimoclock.model.Timings
import ru.vadimoclock.model.TimingsDto
import ru.vadimoclock.service.AudioService
import java.awt.PageAttributes.MediaType
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.net.URLConnection

@RestController
@RequestMapping("/audio")
class AudioController(
    private val audioService: AudioService,
) {

    @PostMapping("/editVolume")
    fun editVolume(@RequestBody dto: VolumeEditFileDto) =
        audioService.editVolumeFile(dto)


    @PostMapping("/editTempo")
    fun editTempo(@RequestBody dto: TempoEditFileDto) =
        audioService.editTempoFile(dto)


    @PostMapping("/mesh")
    fun mesh(@RequestBody dto: MeshFilesDto) =
        audioService.mesh(dto)


    @PostMapping("/union")
    fun union(@RequestBody dto: UnionFilesDto) =
        audioService.union(dto)


    @CrossOrigin
    @RequestMapping(
        path = ["/test"],
        method = [RequestMethod.POST],
        consumes = ["multipart/form-data"]
    )
    fun test(
        response: HttpServletResponse,
        @RequestPart("file") @Valid @NotNull file: MultipartFile,
        @RequestPart("timings") @Valid @NotNull timingsDto: TimingsDto
    ) {

        val timings = Timings(timingsDto)
        val requestModel = CutRequestModel(file, timings)
        val cutFile = audioService.cutFile(requestModel)
        val resource = FileSystemResource(cutFile)

        val mimeType = URLConnection.guessContentTypeFromName(cutFile.name) ?: "application/octet-stream"

        response.addHeader(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.filename + "\"")
        response.contentType = mimeType
        response.setContentLength(cutFile.length().toInt())
        val inputStream = BufferedInputStream(FileInputStream(cutFile))
        FileCopyUtils.copy(inputStream, response.outputStream)
        response.outputStream.flush()
    }

//    @CrossOrigin
//    @RequestMapping(
//        path = ["/test"],
//        method = [RequestMethod.POST],
//        consumes = ["multipart/form-data"]
//    )
//    fun test(
//        @RequestPart("file") @Valid @NotNull file: MultipartFile,
//        @RequestPart("timings") @Valid @NotNull timingsDto: TimingsDto
//    ): ResponseEntity<InputStreamResource> {
//
//        val timings = Timings(timingsDto)
//        val requestModel = CutRequestModel(file, timings)
//        val cutFile = audioService.cutFile(requestModel)
//        val resource = FileSystemResource(cutFile)
////        cutFile.delete()
//
//        response.addHeader(
//            HttpHeaders.CONTENT_DISPOSITION,
//            "attachment; filename=\"" + resource.filename + "\""
//        )
//
//        val inputStream = BufferedInputStream(FileInputStream(cutFile))
//        FileCopyUtils.copy(inputStream, response.outputStream)
//        response.outputStream.flush()
//    }


}