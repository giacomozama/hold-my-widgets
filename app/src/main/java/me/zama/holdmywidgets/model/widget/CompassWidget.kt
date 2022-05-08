package me.zama.holdmywidgets.model.widget

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.zama.holdmywidgets.model.compass.CompassLiveProvider
import javax.inject.Inject

class CompassWidget(private val compassLiveProvider: CompassLiveProvider) : Widget(WidgetDescription.Compass) {

    override fun getContents(context: Context): Flow<List<String>> =
        compassLiveProvider.getLiveDegreesNorth().map {
            val degs = it.toInt()
            val primes = ((it - degs) * 60).toInt()
            val seconds = (((it - degs) * 60 - primes) * 60).toInt()
            listOf("$degs° $primes' $seconds\" N")
        }

    class Factory @Inject constructor(
        private val compassLiveProvider: CompassLiveProvider
    ) : WidgetFactory<CompassWidget> {

        override fun create() = CompassWidget(compassLiveProvider)
    }
}