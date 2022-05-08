package me.zama.holdmywidgets.model.time

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import java.time.LocalDateTime
import javax.inject.Inject

class TimeLiveProvider @Inject constructor() {

    fun getLiveTime() = flow<LocalDateTime> {
        while (currentCoroutineContext().isActive) {
            emit(LocalDateTime.now())
            delay(1000L)
        }
    }
}