package com.test.common.safe

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// launchSafe is a method to launch coroutine safely
// it will catch any exception and return it to onError
// if there is no exception, it will return success to onSuccess
// I use this method in view models
class SafeExt {

    companion object {

        fun CoroutineScope.launchSafe(
            onError: (Throwable) -> Unit = {},
            onSuccess: suspend () -> Unit,
        ): Job {
            val handler = CoroutineExceptionHandler { _, throwable ->
                onError(throwable)
            }

            return launch(handler) {
                onSuccess()
            }
        }

    }

}