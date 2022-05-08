package me.zama.holdmywidgets.model.widget

import androidx.annotation.StringRes
import me.zama.holdmywidgets.R

enum class WidgetDescription(val id: Int, @StringRes val title: Int) {
    Compass(0, R.string.title_compass),
    Coords(1, R.string.title_coords),
    IpAddress(2, R.string.title_ip),
    Clock(3, R.string.title_clock);

    companion object {

        private val idToWidgetMapping = mapOf(
            0 to Compass,
            1 to Coords,
            2 to IpAddress,
            3 to Clock
        )

        fun getById(id: Int) =
            idToWidgetMapping.getOrElse(id) { throw IllegalArgumentException("Invalid ID") }
    }
}