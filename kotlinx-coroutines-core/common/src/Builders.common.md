* `fun CoroutineScope.launch(): Job {}`
  * == coroutine builder
  * launches a NEW coroutine / 
    * 👀NO block the current thread👀
      * == -- concurrent with the -- REST of the code 
    * returns a reference to the coroutine -- as a -- `Job`
      * if job is cancelled (`Job.cancel`) -> coroutine is cancelled
    * coroutine context -- is inherited from a -- `CoroutineScope`
      * if you want to add context elements -> specify -- via -- `context` argument
* TODO:
* If the context does not have any dispatcher nor any other [ContinuationInterceptor], then [Dispatchers.Default] is used.
* The parent job is inherited from a [CoroutineScope] as well, but it can also be overridden
* with a corresponding [context] element.
*
* By default, the coroutine is immediately scheduled for execution.
* Other start options can be specified via `start` parameter. See [CoroutineStart] for details.
* An optional [start] parameter can be set to [CoroutineStart.LAZY] to start coroutine _lazily_. In this case,
* the coroutine [Job] is created in _new_ state. It can be explicitly started with [start][Job.start] function
* and will be started implicitly on the first invocation of [join][Job.join].
*
* Uncaught exceptions in this coroutine cancel the parent job in the context by default
* (unless [CoroutineExceptionHandler] is explicitly specified), which means that when `launch` is used with
* the context of another coroutine, then any uncaught exception leads to the cancellation of the parent coroutine.
*
* See [newCoroutineContext] for a description of debugging facilities that are available for a newly created coroutine.

* TODO: