* TODO:
* `suspend fun delay(timeMillis: Long) {}`
  * == suspending function /
    * is cancellable
      * if the current coroutine's `Job` is cancelled | this suspending function is waiting -> this function -- immediately  resumes with -- `CancellationException`
        * **prompt cancellation guarantee**
          * ALTHOUGH this function is ready to return the result, BUT was cancelled | suspended -> `CancellationException` will be thrown
  * allows
    * delays the coroutine >= `timeMillis` /
      * NO block a thread
      * resumes the thread | AFTER a specified time
      * if `timeMillis` <0 -> function returns immediately
      * if you want to delay forever ( OR | TILL cancellation) -> use `awaitCancellation`
  * uses
    * | `select` invocation
      * `SelectBuilder.onTimeout`

* TODO: