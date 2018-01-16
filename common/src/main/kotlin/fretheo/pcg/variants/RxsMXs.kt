/*
 *     Copyright 2018       Serge Khlebnikov <khlebnikov.sergey@gmail.com>,
 *               2014-2017  Melissa O'Neill <oneill@pcg-random.org>,
 *                          and the PCG Project contributors
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