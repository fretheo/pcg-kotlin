package fretheo.pcg.variants

import fretheo.pcg.PCG
import fretheo.pcg.util.ushr
import fretheo.pcg.util.xsh

/** XSH RS: high xor-shift, followed by a random shift.
 *  Fast. A good performer. */
class XshRs internal constructor(seed: Long, inc: Long) : PCG<Int>(seed, inc) {
    override fun int() = next()

    override fun output() = state.run {
        xsh(22) ushr (ushr(61) + 22)
    }.toInt()
}
