package me.zama.holdmywidgets.model.coords

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface CoordsLiveProvider {
    fun getLiveCoords(): Flow<Result<Location>>
}