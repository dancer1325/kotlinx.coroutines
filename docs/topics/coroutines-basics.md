<!--- TEST_NAME BasicsGuideTest -->
<contribute-url>https://github.com/Kotlin/kotlinx.coroutines/edit/master/docs/topics/</contribute-url>

[//]: # (title: Coroutines basics)

* goal
  * basic coroutine concepts

## Your first coroutine

* _coroutine_
  * 👀== instance of a suspendable computation 👀
  * vs thread
    * (== about) block of code to run is taken / works -- concurrently with the -- rest of the code
    * (!= about) 
      * ❌coroutine -- is NOT bound to -- any particular thread ❌
        * Reason: 🧠 coroutine can
          * suspend its execution | thread1
          * resume its execution | thread2 🧠 
  * == light-weight threads / important differences

* _Example1:_ kotlinx-coroutines-core/jvm/test/guide/example-basic-01.kt
  * kotlin-min-compiler-version="1.3"
  * run it
    * via IDE
    * via CLI -- TODO: -- 
  * output
  
    ```text
    Hello
    World!
    ```

  * see
    * [launch] 
    * [delay] 
    * [runBlocking]

### Structured concurrency

Coroutines follow a principle of 
**structured concurrency** which means that new coroutines can only be launched in a specific [CoroutineScope]
which delimits the lifetime of the coroutine. The above example shows that [runBlocking] establishes the corresponding
scope and that is why the previous example waits until `World!` is printed after a second's delay and only then exits.

In a real application, you will be launching a lot of coroutines. Structured concurrency ensures that they are not
lost and do not leak. An outer scope cannot complete until all its children coroutines complete. 
Structured concurrency also ensures that any errors in the code are properly reported and are never lost.  

## Extract function refactoring

* TODO:
Let's extract the block of code inside `launch { ... }` into a separate function. When you
perform "Extract function" refactoring on this code, you get a new function with the `suspend` modifier.
This is your first _suspending function_. Suspending functions can be used inside coroutines
just like regular functions, but their additional feature is that they can, in turn,
use other suspending functions (like `delay` in this example) to _suspend_ execution of a coroutine.

```kotlin
import kotlinx.coroutines.*

//sampleStart
fun main() = runBlocking { // this: CoroutineScope
    launch { doWorld() }
    println("Hello")
}

// this is your first suspending function
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}
//sampleEnd
```
{kotlin-runnable="true" kotlin-min-compiler-version="1.3"}
<!--- KNIT example-basic-02.kt -->
> You can get the full code [here](https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/jvm/test/guide/example-basic-02.kt).
>
{style="note"}

<!--- TEST
Hello
World!
-->

## Scope builder

In addition to the coroutine scope provided by different builders, it is possible to declare your own scope using the
[coroutineScope][_coroutineScope] builder. It creates a coroutine scope and does not complete until all launched children complete.

[runBlocking] and [coroutineScope][_coroutineScope] builders may look similar because they both wait for their body and all its children to complete.
The main difference is that the [runBlocking] method _blocks_ the current thread for waiting,
while [coroutineScope][_coroutineScope] just suspends, releasing the underlying thread for other usages.
Because of that difference, [runBlocking] is a regular function and [coroutineScope][_coroutineScope] is a suspending function.

You can use `coroutineScope` from any suspending function. 
For example, you can move the concurrent printing of `Hello` and `World` into a `suspend fun doWorld()` function: 

```kotlin
import kotlinx.coroutines.*

//sampleStart
fun main() = runBlocking {
    doWorld()
}

suspend fun doWorld() = coroutineScope {  // this: CoroutineScope
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello")
}
//sampleEnd
```
{kotlin-runnable="true" kotlin-min-compiler-version="1.3"}
<!--- KNIT example-basic-03.kt -->
> You can get the full code [here](https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/jvm/test/guide/example-basic-03.kt).
>
{style="note"}

This code also prints:

```text
Hello
World!
```

<!--- TEST -->

## Scope builder and concurrency

A [coroutineScope][_coroutineScope] builder can be used inside any suspending function to perform multiple concurrent operations.
Let's launch two concurrent coroutines inside a `doWorld` suspending function:

```kotlin
import kotlinx.coroutines.*

//sampleStart
// Sequentially executes doWorld followed by "Done"
fun main() = runBlocking {
    doWorld()
    println("Done")
}

// Concurrently executes both sections
suspend fun doWorld() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}
//sampleEnd
```
{kotlin-runnable="true" kotlin-min-compiler-version="1.3"}
<!--- KNIT example-basic-04.kt -->
> You can get the full code [here](https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/jvm/test/guide/example-basic-04.kt).
>
{style="note"}

Both pieces of code inside `launch { ... }` blocks execute _concurrently_, with 
`World 1` printed first, after a second from start, and `World 2` printed next, after two seconds from start.
A [coroutineScope][_coroutineScope] in `doWorld` completes only after both are complete, so `doWorld` returns and 
allows `Done` string to be printed only after that:

```text
Hello
World 1
World 2
Done
```

<!--- TEST -->

## An explicit job

A [launch] coroutine builder returns a [Job] object that is a handle to the launched coroutine and can be 
used to explicitly wait for its completion. For example, you can wait for completion of the child coroutine
and then print "Done" string:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
//sampleStart
    val job = launch { // launch a new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello")
    job.join() // wait until child coroutine completes
    println("Done") 
//sampleEnd    
}
```
{kotlin-runnable="true" kotlin-min-compiler-version="1.3"}
<!--- KNIT example-basic-05.kt -->
> You can get the full code [here](https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/jvm/test/guide/example-basic-05.kt).
>
{style="note"}

This code produces: 

```text
Hello
World!
Done
```

<!--- TEST -->

## Coroutines are light-weight

Coroutines are less resource-intensive than JVM threads. Code that exhausts the
JVM's available memory when using threads can be expressed using coroutines
without hitting resource limits. For example, the following code launches
50,000 distinct coroutines that each waits 5 seconds and then prints a period
('.') while consuming very little memory:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    repeat(50_000) { // launch a lot of coroutines
        launch {
            delay(5000L)
            print(".")
        }
    }
}
```
{kotlin-runnable="true" kotlin-min-compiler-version="1.3"}
<!--- KNIT example-basic-06.kt -->
> You can get the full code [here](https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/jvm/test/guide/example-basic-06.kt).
>
{style="note"}

<!--- TEST lines.size == 1 && lines[0] == ".".repeat(50_000) -->

If you write the same program using threads (remove `runBlocking`, replace
`launch` with `thread`, and replace `delay` with `Thread.sleep`), it will
consume a lot of memory. Depending on your operating system, JDK version, 
and its settings, it will either throw an out-of-memory error or start threads slowly 
so that there are never too many concurrently running threads. 

<!--- MODULE kotlinx-coroutines-core -->
<!--- INDEX kotlinx.coroutines -->

[launch]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html
[delay]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/delay.html
[runBlocking]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/run-blocking.html
[CoroutineScope]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/index.html
[_coroutineScope]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/coroutine-scope.html
[Job]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html

<!--- END -->
