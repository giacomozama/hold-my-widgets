package me.zama.holdmywidgets.model.compass

import kotlinx.coroutines.flow.Flow

interface CompassLiveProvider {
    fun getLiveDegreesNorth(): Flow<Float>
}