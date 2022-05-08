package me.zama.holdmywidgets.mock.coords

import android.location.Location
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.zama.holdmywidgets.model.coords.CoordsLiveProvider

class CoordsLiveProviderTestImpl: CoordsLiveProvider {

    override fun getLiveCoords(): Flow<Result<Location>> = flowOf(Result.failure(Exception("TEST")))
}