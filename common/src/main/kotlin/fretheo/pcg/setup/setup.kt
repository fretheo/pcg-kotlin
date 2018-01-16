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

package fretheo.pcg.setup

import fretheo.pcg.IRandom
import fretheo.pcg.PCG
import fretheo.pcg.PCG.Companion.PCG_INCREMENT
import fretheo.pcg.util.uniqueNumber

// ----------------------------------------------------------------------------------------
// Four approaches of setting up the PCG algorithm variations
// ----------------------------------------------------------------------------------------

inline fun <T : PCG<I>, I> oneSeq(pcg: (Long, Long) -> T, seed: Long): IRandom
        = pcg(seed, PCG_INCREMENT)

inline fun <T : PCG<I>, I> mcg(pcg: (Long, Long) -> T, seed: Long): IRandom
        = pcg(seed or 3, 0)

inline fun <T : PCG<I>, I> unique(pcg: (Long, Long) -> T, seed: Long): IRandom
        = pcg(seed, uniqueNumber().toLong() or 1)

inline fun <T : PCG<I>, I> setSeq(pcg: (Long, Long) -> T, seed: Long, inc: Long): IRandom
        = pcg(seed, inc.shl(1) or 1)

// ----------------------------------------------------------------------------------------
// In case we need more flexible or "lazy" creation of the RNG engine
// ----------------------------------------------------------------------------------------

inline fun <T : PCG<I>, I> oneSeq(crossinline pcg: (Long, Long) -> T): (Long) -> IRandom
        = { seed: Long -> pcg(seed, PCG_INCREMENT) }

inline fun <T : PCG<I>, I> mcg(crossinline pcg: (Long, Long) -> T): (Long) -> IRandom
        = { seed: Long -> pcg(seed or 3, 0) }

inline fun <T : PCG<I>, I> unique(crossinline pcg: (Long, Long) -> T): (Long) -> IRandom
        = { seed: Long -> pcg(seed, uniqueNumber().toLong() or 1) }

inline fun <T : PCG<I>, I> setSeq(crossinline pcg: (Long, Long) -> T): (Long, Long) -> IRandom
        = { seed: Long, inc: Long -> pcg(seed, inc.shl(1) or 1) }

