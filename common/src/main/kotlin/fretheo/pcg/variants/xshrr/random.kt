package fretheo.pcg.variants.xshrr

import fretheo.pcg.IRandom
import fretheo.pcg.setup.unique
import fretheo.pcg.util.uniqueNumber
import fretheo.pcg.variants.XshRr

/** [XshRr]: high xor-shift, followed by a random rotate.
 *  For stream/sequence generation 'unique' approach is used */
fun Random(seed: Number = uniqueNumber()): IRandom
        = unique(::XshRr, seed.toLong())
