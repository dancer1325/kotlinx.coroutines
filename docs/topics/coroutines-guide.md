<contribute-url>https://github.com/Kotlin/kotlinx.coroutines/edit/master/docs/topics/</contribute-url>


[//]: # (title: Coroutines guide)

* goal
  * core features of `kotlinx.coroutines` + examples

* Kotlin 
  * ONLY minimal low-level APIs | its standard library / 
    * -- enable other libraries, to utilize -- coroutines
  * `async` and `await`
    * | Kotlin (DIFFERENT vs other languages)
      * NOT keywords
      * NOT part of its standard library 
  * _suspending function_
    * == Kotlin's concept of abstraction for async operations /
      * vs futures & promises
        * safer
        * less error-prone  

* `kotlinx.coroutines`
  * == rich library for coroutines
    * developed by JetBrains
  * == high-level coroutine-enabled primitives
    * _Example:_ `launch`, `async`, ... 
  * how to use it?
    * see [README.md](/README.md#how-to-use--your-projects)

## Table of contents

* [Coroutines basics](coroutines-basics.md)
* [Hands-on: Intro to coroutines and channels](https://play.kotlinlang.org/hands-on/Introduction%20to%20Coroutines%20and%20Channels)
* [Cancellation and timeouts](cancellation-and-timeouts.md)
* [Composing suspending functions](composing-suspending-functions.md)
* [Coroutine context and dispatchers](coroutine-context-and-dispatchers.md)
* [Asynchronous Flow](flow.md)
* [Channels](channels.md)
* [Coroutine exceptions handling](exception-handling.md)
* [Shared mutable state and concurrency](shared-mutable-state-and-concurrency.md)
* [Select expression (experimental)](select-expression.md)
* [Tutorial: Debug coroutines using IntelliJ IDEA](debug-coroutines-with-idea.md)
* [Tutorial: Debug Kotlin Flow using IntelliJ IDEA](debug-flow-with-idea.md)

## Additional references

* [Guide to UI programming with coroutines](https://github.com/Kotlin/kotlinx.coroutines/blob/master/ui/coroutines-guide-ui.md)
* [Coroutines design document (KEEP)](https://github.com/Kotlin/KEEP/blob/master/proposals/coroutines.md)
* [Full kotlinx.coroutines API reference](https://kotlinlang.org/api/kotlinx.coroutines/)
* [Best practices for coroutines in Android](https://developer.android.com/kotlin/coroutines/coroutines-best-practices)
* [Additional Android resources for Kotlin coroutines and flow](https://developer.android.com/kotlin/coroutines/additional-resources)
