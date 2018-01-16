package fretheo.pcg.util

/** Shifts value right by the [bitCount] bumber of bits and performs [Int.xor] between result and original value */
internal infix fun Int.xsh(bitCount: Int)
        = ushr(bitCount) xor this

/** Shifts value right by the [bitCount] bumber of bits and performs [Long.xor] between result and original value */
internal infix fun Long.xsh(bitCount: Int)
        = ushr(bitCount) xor this

/** Shifts value right by the [bitCount] bumber of bits and performs [Long.xor] between result and original value */
internal infix fun Long.xsh(bitCount: Long)
        = ushr(bitCount) xor this

/** An extension for [Long.ushr], hides [bitCount] casting to [Int] */
internal infix fun Long.ushr(bitCount: Number)
        = ushr(bitCount.toInt())

/** Returns a [Sequence] with give amount of elements. The returned sequence is constrained to be iterated only once.
 *  @see constrainOnce
 *  @see generateSequence */
internal inline fun <T : Any> generateFixedSizeSequence(size: Int, crossinline nextFunction: () -> T?): Sequence<T> {
    var idx = 0
    return generateSequence {
        if ((++idx) <= size) nextFunction() else null
    }
}

/** Returns value that supposed to be unique and a bit randomized.
 *  For JVM : returns address of new [Any] object;
 *  For JS  : returns randomized hash number, check getObjectHashCode() in "kotlin.js" for more info */
fun uniqueNumber(): Int = Any().hashCode()
