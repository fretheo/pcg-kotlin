/*
 *    Copyright 2018  Serge Khlebnikov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

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
