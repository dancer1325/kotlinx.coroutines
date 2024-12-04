* `expect fun <T> runBlocking(): T`
  * == coroutine builder
  * runs a NEW coroutine /
    * ⚠️**blocks** the CURRENT thread | UNTIL new coroutine completion ⚠️
  * goal
    * 👀regular blocking code -- is bridged to -- libraries / written in suspending style 👀
      * _Example of regular blocking code:_ `fun main()`
        * see [example-basic-01.kt](../../jvm/test/guide/example-basic-01.kt)
      * libraries / written in suspending style
        * == into `runBlocking { ... }`
  * uses 
    * `main` functions
      * == top-level of the application
        * NOT recommended | real code
          * Reason: 🧠threads are expensive resources & blocking them is inefficient 🧠
    * tests
  * ⚠️if you call `runBlocking` | suspend function -> it's redundant ⚠️
    * _Example:_ incorrect

    ```
    suspend fun loadConfiguration() {
        // DO NOT DO THIS:
        val data = runBlocking { // <- redundant and blocks the thread, do not do that -> potential thread starvation issues
            fetchConfigurationData() // suspending function
        }
    ```
        