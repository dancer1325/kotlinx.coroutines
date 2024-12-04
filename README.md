# kotlinx.coroutines 

[![Kotlin Stable](https://kotl.in/badges/stable.svg)](https://kotlinlang.org/docs/components-stability.html)
[![JetBrains official project](https://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![Download](https://img.shields.io/maven-central/v/org.jetbrains.kotlinx/kotlinx-coroutines-core/1.9.0)](https://central.sonatype.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core/1.9.0)
[![Kotlin](https://img.shields.io/badge/kotlin-2.0.0-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Slack channel](https://img.shields.io/badge/chat-slack-green.svg?logo=slack)](https://kotlinlang.slack.com/messages/coroutines/)

* == Library / 
  * -- support for --
    * Kotlin coroutines
    * [multiplatform](#multiplatform)
  * companion version -- for the -- Kotlin `2.0.0` release.

```kotlin
suspend fun main() = coroutineScope {
    launch { 
       delay(1000)
       println("Kotlin Coroutines World!") 
    }
    println("Hello")
}
```

* coroutines online playground [here](https://pl.kotl.in/9zva88r7S)

## Modules

* [core](kotlinx-coroutines-core/README.md) &mdash; common coroutines | ALL platforms
  * [launch] and [async] coroutine builders returning [Job] and [Deferred] light-weight futures with cancellation support;
  * [Dispatchers] object with [Main][Dispatchers.Main] dispatcher for Android/Swing/JavaFx (which require the corresponding artifacts in runtime) and Darwin (included out of the box), and [Default][Dispatchers.Default] dispatcher for background coroutines;
  * [delay] and [yield] top-level suspending functions;
  * [Flow] &mdash; cold asynchronous stream with [flow][_flow] builder and comprehensive operator set ([filter], [map], etc);
  * [Channel], [Mutex], and [Semaphore] communication and synchronization primitives;
  * [coroutineScope][_coroutineScope], [supervisorScope][_supervisorScope], [withContext], and [withTimeout] scope builders;
  * [MainScope()] for Android and UI applications;
  * [SupervisorJob()] and [CoroutineExceptionHandler] for supervision of coroutines hierarchies;
  * [select] expression support and more.
* [core/jvm](kotlinx-coroutines-core/jvm/) &mdash; additional core features available on Kotlin/JVM:
  * [Dispatchers.IO] dispatcher for blocking coroutines;
  * [Executor.asCoroutineDispatcher][asCoroutineDispatcher] extension, custom thread pools, and more;
  * Integrations with `CompletableFuture` and JVM-specific extensions.
* [core/js](kotlinx-coroutines-core/js/) &mdash; additional core features available on Kotlin/JS:
  * Integration with `Promise` via [Promise.await] and [promise] builder;
  * Integration with `Window` via [Window.asCoroutineDispatcher], etc.
* [test](kotlinx-coroutines-test/README.md) &mdash; test utilities for coroutines:
  * [Dispatchers.setMain] to override [Dispatchers.Main] in tests;
  * [runTest] and [TestScope] to test suspending functions and coroutines.
* [debug](kotlinx-coroutines-debug/README.md) &mdash; debug utilities for coroutines:
  * [DebugProbes] API to probe, keep track of, print and dump active coroutines;
  * [CoroutinesTimeout] test rule to automatically dump coroutines on test timeout.
  * Automatic integration with [BlockHound](https://github.com/reactor/BlockHound).
* [reactive](reactive/README.md) &mdash; modules that provide builders and iteration support for various reactive streams libraries:
  * Reactive Streams ([Publisher.collect], [Publisher.awaitSingle], [kotlinx.coroutines.reactive.publish], etc), 
  * Flow (JDK 9) (the same interface as for Reactive Streams),
  * RxJava 2.x ([rxFlowable], [rxSingle], etc), and
  * RxJava 3.x ([rxFlowable], [rxSingle], etc), and
  * Project Reactor ([flux], [mono], etc).
* [ui](ui/README.md) &mdash; modules that provide the [Main][Dispatchers.Main] dispatcher for various single-threaded UI libraries:
  * Android, JavaFX, and Swing.
* [integration](integration/README.md) &mdash; modules that provide integration with various asynchronous callback- and future-based libraries:
  * Guava [ListenableFuture.await], and Google Play Services [Task.await];
  * SLF4J MDC integration via [MDCContext].

## Documentation

* Presentations and videos:
  * [Kotlin Coroutines in Practice](https://www.youtube.com/watch?v=a3agLJQ6vt8) (Roman Elizarov at KotlinConf 2018, [slides](https://www.slideshare.net/elizarov/kotlin-coroutines-in-practice-kotlinconf-2018))
    * TODO: is there repo hosting it? NOT [this](https://github.com/elizarov/CoroutinesWorkshop) nor [this](https://github.com/Kotlin/coroutines-workshop)
  * [Deep Dive into Coroutines](https://www.youtube.com/watch?v=YrrUCSi72E8) (Roman Elizarov at KotlinConf 2017, [slides](https://www.slideshare.net/elizarov/deep-dive-into-coroutines-on-jvm-kotlinconf-2017))
    * [Github repo](https://github.com/dancer1325/kotlin-coroutines-workshop)
  * [History of Structured Concurrency in Coroutines](https://www.youtube.com/watch?v=Mj5P47F6nJg) (Roman Elizarov at Hydra 2019, [slides](https://speakerdeck.com/elizarov/structured-concurrency))
    * TODO:
* Guides and manuals: 
  * [Guide to kotlinx.coroutines by example](https://kotlinlang.org/docs/coroutines-guide.html) (**read it first**)
  * [Guide to UI programming with coroutines](ui/coroutines-guide-ui.md)
  * [Debugging capabilities in kotlinx.coroutines](docs/topics/debugging.md)
* [Compatibility policy and experimental annotations](docs/topics/compatibility.md)
* [Change log for kotlinx.coroutines](CHANGES.md)
* [Coroutines design document (KEEP)](https://github.com/Kotlin/KEEP/blob/master/proposals/coroutines.md)
* [Full kotlinx.coroutines API reference](https://kotlinlang.org/api/kotlinx.coroutines/)

 
## How to use | your projects?

### Maven

* add dependencies
  * add `kotlinx-coroutines-core`

    ```xml
    <dependency>
        <groupId>org.jetbrains.kotlinx</groupId>
        <artifactId>kotlinx-coroutines-core</artifactId>
        <version>1.9.0</version>
    </dependency>
    ```

  * if you need other modules -> add them   
* use the latest Kotlin version

  ```xml
  <properties>
      <kotlin.version>2.0.0</kotlin.version>
  </properties>
  ```

### Gradle

* add dependencies
  * add `kotlinx-coroutines-core`

    ```kotlin
    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    }
    ```

  * if you need other modules -> add them
* use the latest Kotlin version

  ```kotlin
  plugins {
      // For build.gradle.kts (Kotlin DSL)
      kotlin("jvm") version "2.0.0"
        
      // For build.gradle (Groovy DSL)
      id "org.jetbrains.kotlin.jvm" version "2.0.0"
  }
  ```

* use `mavenCentral()` | list of repositories

  ```kotlin
  repositories {
      mavenCentral()
  }
  ```

### Android

* add dependencies
  * [`kotlinx-coroutines-android`](ui/kotlinx-coroutines-android)

  ```kotlin
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")
  ```

    * -> access to [Dispatchers.Main]
      * == Android coroutine dispatcher

#### R8 and ProGuard

* included | [`kotlinx-coroutines-android`](ui/kotlinx-coroutines-android) module
* see ["Optimization" section for Android](ui/kotlinx-coroutines-android/README.md#optimization)

#### Avoiding including the debug infrastructure | resulting APK

* `kotlinx-coroutines-core` artifact -- contains a -- resource file / 
  * NOT required for the coroutines to operate
  * ONLY used by the debugger 
  * 👀recommendations👀
    * excluded it

```kotlin
android {
    ...
    packagingOptions {
        resources.excludes += "DebugProbesKt.bin"
    }
}
```

### Multiplatform

* `kotlinx-coroutines-core`
  * ALSO available for
    * [Kotlin/JS](https://kotlinlang.org/docs/reference/js-overview.html)
    * [Kotlin/Native](https://kotlinlang.org/docs/reference/native-overview.html)

* | common code / should get compiled for different platforms -> add a dependency to `kotlinx-coroutines-core` | `commonMain` source set

  ```kotlin
  commonMain {
      dependencies {
          implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
      }
  }
  ```

* recommendations
  * Platform-specific dependencies | (ONLY) non-multiplatform projects / -- are compiled ONLY for -- target platform

#### JS

* == Kotlin/JS version of `kotlinx.coroutines`
  * [`kotlinx-coroutines-core-js`](https://central.sonatype.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core-js/1.9.0)
    * see in the previous link how to add the dependency

#### Native

* == Kotlin/Native version of `kotlinx.coroutines`
  * [`kotlinx-coroutines-core-$platform`](https://central.sonatype.com/search?q=kotlinx-coroutines-core&namespace=org.jetbrains.kotlinx) /
    * `$platform` == target Kotlin/Native platform 
      * Targets -- are provided in accordance with -- [official K/N target support](https://kotlinlang.org/docs/native-target-support.html)

## Building and Contributing

See [Contributing Guidelines](CONTRIBUTING.md).

<!--- MODULE kotlinx-coroutines-core -->
<!--- INDEX kotlinx.coroutines -->

[launch]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html
[async]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html
[Job]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html
[Deferred]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/index.html
[Dispatchers]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/index.html
[Dispatchers.Main]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-main.html
[Dispatchers.Default]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-default.html
[delay]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/delay.html
[yield]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/yield.html
[_coroutineScope]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/coroutine-scope.html
[_supervisorScope]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/supervisor-scope.html
[withContext]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/with-context.html
[withTimeout]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/with-timeout.html
[MainScope()]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-main-scope.html
[SupervisorJob()]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-supervisor-job.html
[CoroutineExceptionHandler]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-exception-handler/index.html
[Dispatchers.IO]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-i-o.html
[asCoroutineDispatcher]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/as-coroutine-dispatcher.html
[Promise.await]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/await.html
[promise]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/[js]promise.html
[Window.asCoroutineDispatcher]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/as-coroutine-dispatcher.html

<!--- INDEX kotlinx.coroutines.flow -->

[Flow]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/index.html
[_flow]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/flow.html
[filter]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/filter.html
[map]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/map.html

<!--- INDEX kotlinx.coroutines.channels -->

[Channel]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.channels/-channel/index.html

<!--- INDEX kotlinx.coroutines.selects -->

[select]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.selects/select.html

<!--- INDEX kotlinx.coroutines.sync -->

[Mutex]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.sync/-mutex/index.html
[Semaphore]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.sync/-semaphore/index.html

<!--- MODULE kotlinx-coroutines-test -->
<!--- INDEX kotlinx.coroutines.test -->

[Dispatchers.setMain]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/set-main.html
[runTest]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/run-test.html
[TestScope]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/kotlinx.coroutines.test/-test-scope/index.html

<!--- MODULE kotlinx-coroutines-debug -->
<!--- INDEX kotlinx.coroutines.debug -->

[DebugProbes]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-debug/kotlinx.coroutines.debug/-debug-probes/index.html

<!--- INDEX kotlinx.coroutines.debug.junit4 -->

[CoroutinesTimeout]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-debug/kotlinx.coroutines.debug.junit4/-coroutines-timeout/index.html

<!--- MODULE kotlinx-coroutines-slf4j -->
<!--- INDEX kotlinx.coroutines.slf4j -->

[MDCContext]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-slf4j/kotlinx.coroutines.slf4j/-m-d-c-context/index.html

<!--- MODULE kotlinx-coroutines-jdk8 -->
<!--- INDEX kotlinx.coroutines.future -->
<!--- MODULE kotlinx-coroutines-guava -->
<!--- INDEX kotlinx.coroutines.guava -->

[ListenableFuture.await]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-guava/kotlinx.coroutines.guava/await.html

<!--- MODULE kotlinx-coroutines-play-services -->
<!--- INDEX kotlinx.coroutines.tasks -->

[Task.await]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-play-services/kotlinx.coroutines.tasks/await.html

<!--- MODULE kotlinx-coroutines-reactive -->
<!--- INDEX kotlinx.coroutines.reactive -->

[Publisher.collect]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-reactive/kotlinx.coroutines.reactive/collect.html
[Publisher.awaitSingle]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-reactive/kotlinx.coroutines.reactive/await-single.html
[kotlinx.coroutines.reactive.publish]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-reactive/kotlinx.coroutines.reactive/publish.html

<!--- MODULE kotlinx-coroutines-rx2 -->
<!--- INDEX kotlinx.coroutines.rx2 -->

[rxFlowable]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-rx2/kotlinx.coroutines.rx2/rx-flowable.html
[rxSingle]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-rx2/kotlinx.coroutines.rx2/rx-single.html

<!--- MODULE kotlinx-coroutines-rx2 -->
<!--- INDEX kotlinx.coroutines.rx2 -->
<!--- MODULE kotlinx-coroutines-reactor -->
<!--- INDEX kotlinx.coroutines.reactor -->

[flux]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-reactor/kotlinx.coroutines.reactor/flux.html
[mono]: https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-reactor/kotlinx.coroutines.reactor/mono.html

<!--- END -->
