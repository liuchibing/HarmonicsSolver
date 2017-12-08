package ml.liuchibing.harmonicssolver.adts

/**
 * Key Signature.
 * mode: true for major, false for minor
 */
enum class Key(val fifth: Int, val mode: Boolean) {
    F(-1, true),
    d(-1, false),
    BFlat(-2, true),
    g(-2, false),
    EFlat(-3, true),
    c(-3, false),
    AFlat(-4, true),
    f(-4, false),
    DFlat(-5, true),
    bFlat(-5, false),
    GFlat(-6, true),
    eFlat(-6, false),

    C(0, true),
    a(0, false),

    G(1, true),
    e(1, false),
    D(2, true),
    b(2, false),
    A(3, true),
    fSharp(3, false),
    E(4, true),
    cSharp(4, false),
    B(5, true),
    gSharp(5, false),
    FSharp(6, true),
    dSharp(6, false);

    companion object {
        fun get(fifth: Int, mode: Boolean): Key {
            Key.values()
                    .filter { it.fifth == fifth && it.mode == mode }
                    .forEach { return it }
            throw IllegalArgumentException("Key($fifth, $mode) doesn't exist")
        }
    }
}