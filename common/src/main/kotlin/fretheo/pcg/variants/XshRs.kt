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
