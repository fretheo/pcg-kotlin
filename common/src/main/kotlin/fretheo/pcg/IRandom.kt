package fretheo.pcg

import fretheo.pcg.util.generateFixedSizeSequence

/** Interface represents a class that is used to generate sequences of pseudo-random numbers. */
interface IRandom {
    // ----------------------------------------------------------------------------------------
    // Methods for generating single pseudo-random number of each type
    // ----------------------------------------------------------------------------------------

    fun int(): Int

    fun long(): Long
            = (int().toLong() shl 32) + int()

    fun byte(): Byte
            = (float() * 0xFF).toByte()

    fun short(): Short
            = (float() * 0xFFFF).toShort()

    fun float(): Float
            = (int() ushr 5) / (1L shl 27).toFloat()

    fun double(): Double
            = (long() ushr 11) / (1L shl 53).toDouble()

    fun boolean(): Boolean
            = float() < 0.5

    // ----------------------------------------------------------------------------------------
    // Methods for generating pseudo-random numbers with given boundary
    // ----------------------------------------------------------------------------------------

    fun byte(bound: Byte): Byte
            = (float() * bound).toByte()

    fun short(bound: Short): Short
            = (float() * bound).toShort()

    fun int(bound: Int): Int
            = (float() * bound).toInt()

    fun long(bound: Long): Long
            = (double() * bound).toLong()

    // ----------------------------------------------------------------------------------------
    // Methods for generating sequences of pseudo-random numbers
    // ----------------------------------------------------------------------------------------

    fun bytes(): Sequence<Byte>
            = generateSequence { byte() }

    fun shorts(): Sequence<Short>
            = generateSequence { short() }

    fun ints(): Sequence<Int>
            = generateSequence { int() }

    fun longs(): Sequence<Long>
            = generateSequence { long() }

    fun floats(): Sequence<Float>
            = generateSequence { float() }

    fun doubles(): Sequence<Double>
            = generateSequence { double() }

    fun booleans(): Sequence<Boolean>
            = generateSequence { boolean() }

    // ----------------------------------------------------------------------------------------
    // Methods for generating fixed-sized sequences of pseudo-random numbers
    // ----------------------------------------------------------------------------------------

    fun bytes(size: Int): Sequence<Byte>
            = generateFixedSizeSequence(size) { byte() }

    fun shorts(size: Int): Sequence<Short>
            = generateFixedSizeSequence(size) { short() }

    fun ints(size: Int): Sequence<Int>
            = generateFixedSizeSequence(size) { int() }

    fun longs(size: Int): Sequence<Long>
            = generateFixedSizeSequence(size) { long() }

    fun floats(size: Int): Sequence<Float>
            = generateFixedSizeSequence(size) { float() }

    fun doubles(size: Int): Sequence<Double>
            = generateFixedSizeSequence(size) { double() }

    fun booleans(size: Int): Sequence<Boolean>
            = generateFixedSizeSequence(size) { boolean() }
}