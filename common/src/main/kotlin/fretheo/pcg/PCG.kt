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

package fretheo.pcg

/** Class represents a base class for various implementations
 *  of permuted congruential generator (PCG) algorithm. */
abstract class PCG<T>(seed: Long, inc: Long) : IRandom where T : Number,
                                                             T : Comparable<T> {
    // ----------------------------------------------------------------------------------------
    // Define generator state and perform the initial setup
    // ----------------------------------------------------------------------------------------
    protected val increment: Long = inc
    protected var state: Long = inc + seed

    init { step() }

    // ----------------------------------------------------------------------------------------
    // Methods for advancing the state of generator
    // ----------------------------------------------------------------------------------------

    protected fun step() {
        state = state * PCG_MULTIPLIER + increment
    }

    protected fun advance(delta: Long) {
        var (accMult, accPlus) = arrayOf(1L, 0L)
        var (curMult, curPlus) = arrayOf(PCG_MULTIPLIER, increment)
        var curDelta = delta
        while (curDelta != 0L) {
            if (curDelta and 1 != 0L) {
                accMult *= curMult
                accPlus = accPlus * curMult + curPlus
            }
            curPlus *= (curMult + 1)
            curMult *= curMult
            curDelta = curDelta ushr 1
        }
        state = accMult * state + accPlus
    }

    // ----------------------------------------------------------------------------------------
    // Methods for producing output of the generator
    // ----------------------------------------------------------------------------------------
    fun next() = output().also { step() }

    /** Method should define transformation for a state.
     *
     *  Suggested transformation components are:
     *  RR  : random (input-dependent) rotation     ( (x >> const) | (x << (-const & mask)) )
     *  RS  : random (input-dependent) shift        ( x >> const )
     *  XSH : xor-shift operation                   ( x ^ (x >> const) )
     *  XSL : simplified xor-shift, folding value in half by XORing the high half into the low
     *  RXS : xor-shift by a random (input-dependent) amount
     *  M   : multiply by a fixed constant */
    abstract fun output(): T

    // ----------------------------------------------------------------------------------------
    // Useful constants, applicable specifically for 64-bit state
    // ----------------------------------------------------------------------------------------
    companion object {
        const val MCG_MULTIPLIER = -5840758589994634535
        const val PCG_MULTIPLIER = 6364136223846793005
        const val PCG_INCREMENT = 1442695040888963407
    }
}
