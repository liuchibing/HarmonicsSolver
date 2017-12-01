package ml.liuchibing.harmonicssolver

import ml.liuchibing.harmonicssolver.adts.Measure
import ml.liuchibing.harmonicssolver.adts.Part
import ml.liuchibing.harmonicssolver.adts.Score
import org.w3c.dom.Element
import org.xml.sax.InputSource
import java.io.InputStream
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class MusicXMLConverter {
    companion object {
        fun parse(src: InputStream) {
            //Create DOM
            val factory = DocumentBuilderFactory.newInstance()
            val builder = factory.newDocumentBuilder()
            builder.setEntityResolver({ publicId, systemId ->
                if (publicId.startsWith("-//Recordare//") || systemId.startsWith("file://")) {
                    val file = systemId.substringAfterLast("/")
                    val stream = MusicXMLConverter::class.java.getResourceAsStream("/musicxml30/$file")
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
            if (partList.length != parts.length) throw Exception("Wrong XML file")
            for (i in 0 until partList.length) {
                val infoNode = partList.item(i) as Element
                val node = parts.item(i) as Element
                if (infoNode.getAttribute("id") != node.getAttribute("id")) throw Exception("Wrong XML File")
                val part = Part(node, infoNode)
                score.parts.add(part)
                //Parse measures
                val measures = node.getElementsByTagName("measure")
                for (iM in 0 until measures.length) {
                    val nodeM = measures.item(i) as Element
                    val measure = Measure(nodeM)
                    part.measures.add(measure)
                    //Parse Voices
                }
            }
        }
    }
}
