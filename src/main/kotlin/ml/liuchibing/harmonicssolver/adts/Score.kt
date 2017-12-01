package ml.liuchibing.harmonicssolver.adts

import org.w3c.dom.Element

class Score(val node: Element) {
    val parts: MutableList<Part> = mutableListOf()
}