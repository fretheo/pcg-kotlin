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

package fretheo.pcg.variants.rxsmxs

import fretheo.pcg.IRandom
import fretheo.pcg.setup.unique
import fretheo.pcg.util.uniqueNumber
import fretheo.pcg.variants.RxsMXs

/** [RxsMXs]: random xor-shift, mcg multiply, fixed xor-shift.
 *  For stream/sequence generation 'unique' approach is used */
fun Random(seed: Number = uniqueNumber()): IRandom
        = unique(::RxsMXs, seed.toLong())
