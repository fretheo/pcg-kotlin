package fretheo.pcg.variants

import fretheo.pcg.PCG
import fretheo.pcg.util.xsh

/** XSH RR: high xor-shift, followed by a random rotate.
 *  Fast. A good performer. Slightly better statistically than [XshRs]. */
class XshRr internal constructor(seed: Long, inc: Long) : PCG<Int>(seed, inc) {
    override fun int() = next()

    override fun output() = state.run {
        val res = xsh(18) ushr 27
        val rot = ushr(59).toInt()
        (res ushr rot) or (res shl ((-rot) and 31))
    }.toInt()
}