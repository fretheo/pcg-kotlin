package fretheo.pcg.variants.xshrs

import fretheo.pcg.IRandom
import fretheo.pcg.setup.unique
import fretheo.pcg.util.uniqueNumber
import fretheo.pcg.variants.XshRs

/** [XshRs]: high xor-shift, followed by a random shift.
 *  For stream/sequence generation 'unique' approach is used */
fun Random(seed: Number = uniqueNumber()): IRandom
        = unique(::XshRs, seed.toLong())
