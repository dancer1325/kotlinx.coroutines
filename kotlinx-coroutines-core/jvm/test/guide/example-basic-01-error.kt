package kotlinx.coroutines.guide.exampleBasic01error

import kotlinx.coroutines.*

// if you remove `runBlocking` -> error because `launch` can be declared | ONLY `CoroutineScope`
fun main() = {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}
