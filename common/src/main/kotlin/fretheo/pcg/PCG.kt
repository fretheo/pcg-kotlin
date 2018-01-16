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
