# PCG - Kotlin implementation

This code provides an implementation of several members of PCG family of pseudo-random number generators.

Complete information about the algorithm as well as reference C++ implementation can be found at the [PCG website](http://www.pcg-random.org/).

Please note, current implementation includes only variations of the algorithm with a state of 64-bits. It doesn't include some useful features from the reference implementation, instead of that it provides an interface that should cover various use-cases.

Resulting artifact supposed to be a kotlin-common library that could be used within either JS or Java environment. For now we have separate artifacts for each respective platform. 

## Getting Started

### Prerequisites

* [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Gradle](https://gradle.org/install/)

Build is verified with these versions:
```
Gradle:  4.4
JVM:     1.8.0_152 (Oracle Corporation 25.152-b16)
```

### Building

Compile the code without running tests:
```
$ gradle assemble
```

### Running tests

All tests are platform-independent and can be found in `common/src/tests` directory.

Subprojects `js/` and `jvm/` don't contain any code, although they're needed for generating artifacts and running tests on each respective platform.

Run tests:
```
$ gradle test
```

Also you can use `build` task for both, assembling and running tests:
```
$ gradle build
```

## Using as a library

### Downloading [![](https://jitpack.io/v/fretheo/pcg-kotlin.svg)](https://jitpack.io/#fretheo/pcg-kotlin/v0.0.1)

Example for Gradle, add JitPack repository:
```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```
Add the dependency:
```gradle
dependencies {
    compile 'com.github.fretheo.pcg-kotlin:pcg-kotlin-js:v0.0.1'

    // or this one for JVM-based project
    // compile 'com.github.fretheo.pcg-kotlin:pcg-kotlin-jvm:v0.0.1'
}
```

### Components

Library provides three variations of the algorithm:
```kotlin
class XshRs  { /* */ }
class XshRr  { /* */ }
class RxsMXs { /* */ }
```
With four initialization techniques for them:
* OneSeq - with predefined fixed increment, it produces pseudo-random numbers from a single sequence
* Mcg - produces pseudo-random numbers from a single sequence adding zero as an increment, has reduced period
* Unique - increment is defined by `Any().hashCode()` call, thus every generator has its own unique sequence
* SetSeq - produces pseudo-random numbers from different sequences due to the increment has to be set by user

Library also provides predefined generators for simple and easy start.

### Usage

General way of using:
```kotlin
fun foo(seed: Long) {
    val rng = oneSeq(::XshRs, seed)

    // single number
    rng.int()

    // sequence of numbers
    rng.ints()
}
```

A bit more simple way:
```kotlin
import fretheo.pcg.variants.xshrs.Random
// or '...xshrr.Random'
// or '...rxsmxs.Random'

fun foo() {
    val rng = Random()

    // single number
    rng.int()

    // sequence of numbers
    rng.ints()
}
```

## Versioning

TODO

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details
