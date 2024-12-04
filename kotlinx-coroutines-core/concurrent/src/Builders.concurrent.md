* `expect fun <T> runBlocking(): T`
  * runs a NEW coroutine /
    * ⚠️**blocks** the CURRENT thread | UNTIL its completion ⚠️
  * goal
    * regular blocking code -- is bridged to -- libraries / written in suspending style
  * uses 
    * `main` functions
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
        