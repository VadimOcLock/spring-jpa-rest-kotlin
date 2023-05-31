package ru.vadimoclock.utils

import ru.vadimoclock.exception.IncorrectTimingsException
import java.io.*
import javax.sound.sampled.AudioFileFormat
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem

class AudioUtils {
    companion object {

        fun cutWav(inputFileStream: InputStream, startSecond: Double, endSecond: Double): File {

            try {

                if (startSecond >= endSecond) throw IncorrectTimingsException()

                val audioInputStream = AudioSystem.getAudioInputStream(inputFileStream)
                val format = audioInputStream.format
                val bytesPerSecond = format.frameSize * format.frameRate.toInt()
                val framesToCopy = (endSecond - startSecond) * format.frameRate

                audioInputStream.skip((startSecond * bytesPerSecond).toLong())
                val audioOutputStream = AudioInputStream(audioInputStream, format, framesToCopy.toLong())
                val file = File("./src/main/resources/files/new.wav")
                AudioSystem.write(audioOutputStream, AudioFileFormat.Type.WAVE, file)
//                val bytes = file.readBytes()

                return file
            } catch (ex: Exception) {
                println(ex.message)
                throw IncorrectTimingsException()
            }
        }

        fun formatTimings(timings: String): Double {

            val splitTimings = timings.split(":").map { it.toDouble() }

            return splitTimings[0] * 3600 + splitTimings[1] * 60 + splitTimings[2]
        }
    }
}