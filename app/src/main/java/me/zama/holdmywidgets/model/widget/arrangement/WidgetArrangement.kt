package me.zama.holdmywidgets.model.widget.arrangement

import me.zama.holdmywidgets.model.widget.Widget

class WidgetArrangement internal constructor(private val widgets: List<Widget>) : List<Widget> by widgets {

    operator fun plus(widget: Widget) = WidgetArrangement(widgets + widget)

    fun without(position: Int) = WidgetArrangement(widgets.filterIndexed { index, _ -> index != position })


    internal fun serialize() = widgets.joinToString(",") { it.id.toString() }


    companion object {

        val Empty = WidgetArrangement(emptyList())
    }
}