package ml.liuchibing.harmonicssolver

import ml.liuchibing.harmonicssolver.adts.Part
import ml.liuchibing.harmonicssolver.adts.Score
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.InputStream
import java.nio.file.Paths
import javax.xml.parsers.DocumentBuilderFactory

class MusicXMLConverter {
    companion object {
        fun parse(src: InputStream) {
            //Create DOM
            val factory = DocumentBuilderFactory.newInstance()
            val builder = factory.newDocumentBuilder()
            builder.setEntityResolver({ publicId, systemId ->
                if (publicId.startsWith("-//Recordare//") || systemId.startsWith("file://")) {
                    val file = Paths.get(systemId)
                    val stream = MusicXMLConverter::class.java.getResourceAsStream("/musicxml30/${file.fileName}")
                    InputSource(stream)
                } else { null }
            })
            val doc = builder.parse(src)

            //Wrap DOM using ADTs
            val root = doc.documentElement
            if (root.nodeName != "score-partwise") throw Exception("Unsupported XML File")
            val score = Score(root)
            //Get parts
            val partList = root.getElementsByTagName("score-part")
            val parts = root.getElementsByTagName("part")
            for (i in 0 until partList.length) {
                val infoNode = partList.item(i) as Element
                val node = parts.item(i) as Element
                score.parts.add(Part(node, infoNode))
            }
        }
    }
}
