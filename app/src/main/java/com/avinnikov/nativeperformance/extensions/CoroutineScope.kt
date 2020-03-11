package com.avinnikov.nativeperformance.extensions

import kotlinx.coroutines.*

fun <T : Any?> launchInCoroutineScope(
        onRun: suspend () -> T,
        onPreRun: () -> Unit = {},
        onPostRun: (T) -> Unit = {},
        onFinally: (Throwable?) -> Unit = {}
) = CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
    onMainThread { onPreRun.invoke() }
    try {
        val result: T = onRun.invoke()
        onMainThread { onPostRun.invoke(result) }
    } catch (e: Throwable) {
        onMainThread { onFinally.invoke(e) }
    } finally {
        onMainThread { onFinally.invoke(null) }
    }
}

private suspend fun onMainThread(onMainThread: () -> Unit) =
        withContext(Dispatchers.Main) { onMainThread.invoke() }