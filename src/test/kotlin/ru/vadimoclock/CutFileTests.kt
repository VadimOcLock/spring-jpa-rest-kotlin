package ru.vadimoclock

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ru.vadimoclock.model.Timings
import ru.vadimoclock.model.TimingsDto
import ru.vadimoclock.utils.AudioUtils
import java.io.File

class CutFileTests {

    @Test
    fun cutWavTest() {

        val filePath = "./src/main/resources/files/cut.wav"
        val inputStream = File(filePath).readBytes().inputStream()
        val file = AudioUtils.cutWav(inputStream, 2.0, 10.0)

        Assertions.assertEquals(true, file.isFile)
    }

    @Test
    fun formatTimingsTest() {

        val inputTimings1 = "01:31:54"
        val formattedTimings1 = AudioUtils.formatTimings(inputTimings1)

        val inputTimings2 = "00:00:12"
        val formattedTimings2 = AudioUtils.formatTimings(inputTimings2)

        val inputTimings3 = "00:00:00"
        val formattedTimings3 = AudioUtils.formatTimings(inputTimings3)

        Assertions.assertEquals(5514.0, formattedTimings1)
        Assertions.assertEquals(12.0, formattedTimings2)
        Assertions.assertEquals(0.0, formattedTimings3)
    }

    @Test
    fun checkTimingFieldsTest() {

        val dto = TimingsDto("00:00:12", "01:31:54")
        val timings = Timings(dto)

        Assertions.assertEquals(12.0, timings.start)
        Assertions.assertEquals(5514.0, timings.end)
    }
}