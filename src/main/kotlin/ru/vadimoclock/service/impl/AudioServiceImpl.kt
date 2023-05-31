package ru.vadimoclock.service.impl

import org.springframework.stereotype.Service
import ru.vadimoclock.dto.*
import ru.vadimoclock.model.CutRequestModel
import ru.vadimoclock.responses.FileResponse
import ru.vadimoclock.service.AudioService
import ru.vadimoclock.utils.AudioUtils
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.SequenceInputStream
import javax.sound.sampled.*
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.FloatControl


@Service
class AudioServiceImpl : AudioService {

    override fun cutFile(dto: CutRequestModel): File {

        val file = AudioUtils.cutWav(dto.file.bytes.inputStream(), dto.timings.start, dto.timings.end)

        return file
    }

    override fun editVolumeFile(dto: VolumeEditFileDto): FileResponse {

        val inputStream = ByteArrayInputStream(dto.file!!.bytes)
        val outputStream = ByteArrayOutputStream()

        val audioInputStream = AudioSystem.getAudioInputStream(inputStream)
        val clip = AudioSystem.getClip()
        clip.open(audioInputStream)
        val gainControl = clip.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl
//        gainControl.value = dto.volume
        AudioSystem.write(AudioInputStream(audioInputStream, audioInputStream.format, audioInputStream.frameLength), AudioFileFormat.Type.WAVE, outputStream)
        val outputByteArray = outputStream.toByteArray()

        return FileResponse(
                outputByteArray,
                201,
                "Success edit volume file"
        )
    }

    override fun editTempoFile(dto: TempoEditFileDto): FileResponse {

//        val inputStream = ByteArrayInputStream(dto.file!!.bytes)
//        val audioStream = TarsosDSPAudioInputStream(inputStream, TarsosDSPAudioFormat(44100, 16, 1, true, false))
//        val dispatcher = AudioDispatcherFactory.fromPipe(audioStream, 1024, 0)
//        val shifter = PitchShifter(2.0f, dispatcher.format.sampleRate.toFloat(), 1024)
//        dispatcher.addAudioProcessor(shifter)
//        val outputByteArray = WaveformWriter(dispatcher.format,"output.wav")
        val outputByteArray = ByteArrayOutputStream().toByteArray()

        return FileResponse(
                outputByteArray,
                201,
                "Success edit tempo file"
        )
    }

    override fun mesh(dto: MeshFilesDto): FileResponse {

//        val mixer = getDispatchers(dto)
        val outputByteArray = ByteArrayOutputStream().toByteArray()
//        WaveformWriter(mixer.format, outputByteArray)

        return FileResponse(
                outputByteArray,
                201,
                "Success mesh files"
        )
    }

    override fun union(dto: UnionFilesDto): FileResponse {

        val clips = dto.files!!.map { AudioSystem.getAudioInputStream(it.inputStream) }

//        val appendedFiles = AudioInputStream(
//                SequenceInputStream(clips),
//                clips[0].format,
//                clips.sumOf { it.frameLength }
//        )

        val outputByteArray = ByteArrayOutputStream().toByteArray()
//        AudioSystem.write(appendedFiles, AudioFileFormat.Type.WAVE, outputByteArray)

        return FileResponse(
                outputByteArray,
                201,
                ""
        )
    }

//    private fun getDispatchers(dto: MeshFileDto): Mixer {
//
//        val dispatchers = dto.files!!.map { AudioDispatcherFactory.fromPipe(it.bytes, 1024, 0) }
//        val mixer = Mixer(2)
//        mixer.addInput(dispatchers)
//
//        return mixer
//    }

}