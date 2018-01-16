package fretheo.pcg.variants.rxsmxs

import fretheo.pcg.IRandom
import fretheo.pcg.setup.unique
import fretheo.pcg.util.uniqueNumber
import fretheo.pcg.variants.RxsMXs

/** [RxsMXs]: random xor-shift, mcg multiply, fixed xor-shift.
 *  For stream/sequence generation 'unique' approach is used */
fun Random(seed: Number = uniqueNumber()): IRandom
        = unique(::RxsMXs, seed.toLong())
