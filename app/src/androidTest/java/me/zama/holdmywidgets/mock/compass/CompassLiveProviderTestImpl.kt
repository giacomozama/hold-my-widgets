package me.zama.holdmywidgets.mock.compass

import kotlinx.coroutines.flow.flowOf
import me.zama.holdmywidgets.model.compass.CompassLiveProvider

class CompassLiveProviderTestImpl : CompassLiveProvider {

    override fun getLiveDegreesNorth() = flowOf(0f)
}