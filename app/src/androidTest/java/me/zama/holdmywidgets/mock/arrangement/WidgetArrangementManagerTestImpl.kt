package me.zama.holdmywidgets.mock.arrangement

import me.zama.holdmywidgets.model.widget.arrangement.WidgetArrangement
import me.zama.holdmywidgets.model.widget.arrangement.WidgetArrangementManager

class WidgetArrangementManagerTestImpl : WidgetArrangementManager {

    override fun store(arrangement: WidgetArrangement) {
    }

    override fun load() = WidgetArrangement.Empty
}