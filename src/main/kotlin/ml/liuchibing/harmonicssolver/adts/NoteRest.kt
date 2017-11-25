package ml.liuchibing.harmonicssolver.adts

data class NoteRest(val noteheads: MutableList<Notehead>, val key: KeySignature, val timeSign: TimeSignature, val rhythmsPosition: Int)