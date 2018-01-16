package fretheo.pcg.variants

import fretheo.pcg.PCG
import fretheo.pcg.util.xsh

/** RXS M XS: random xor-shift, mcg multiply, fixed xor-shift.
 *  The most statistically powerful generator, but all those steps make it slower than some of the others. */
class RxsMXs internal constructor(seed: Long, inc: Long) : PCG<Long>(seed, inc) {
    override fun int(): Int = (double() * 0xFFFFFFFFL).toInt()
    override fun long(): Long = next()

    override fun output() = state.run {
        (xsh(ushr(59) + 5) * MCG_MULTIPLIER) xsh 43
    }
}