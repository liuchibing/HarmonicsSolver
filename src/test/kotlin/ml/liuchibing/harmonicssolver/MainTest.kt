package ml.liuchibing.harmonicssolver

import org.junit.Test

class MainTest {
    @Test fun parseTest() {
        val input = javaClass.getResourceAsStream("/1.xml")
        MusicXMLConverter.parse(input)
    }
}