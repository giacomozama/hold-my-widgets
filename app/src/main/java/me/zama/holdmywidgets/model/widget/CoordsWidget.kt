package me.zama.holdmywidgets.model.widget

import android.content.Context
import kotlinx.coroutines.flow.map
import me.zama.holdmywidgets.R
import me.zama.holdmywidgets.model.coords.CoordsLiveProvider
import javax.inject.Inject

class CoordsWidget(private val coordsLiveProvider: CoordsLiveProvider) : Widget(WidgetDescription.Coords) {

    override fun getContents(context: Context) = coordsLiveProvider.getLiveCoords().map {
        val location = it.getOrNull()
        if (location == null) {
            listOf(it.exceptionOrNull()!!.message!!)
        } else {
            listOf(context.getString(R.string.location_template, location.longitude, location.latitude))
        }
    }

    class Factory @Inject constructor(
        private val coordsLiveProvider: CoordsLiveProvider
    ) : WidgetFactory<CoordsWidget> {

        override fun create() = CoordsWidget(coordsLiveProvider)
    }
}