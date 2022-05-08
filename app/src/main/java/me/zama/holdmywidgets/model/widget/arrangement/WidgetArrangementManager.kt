package me.zama.holdmywidgets.model.widget.arrangement

interface WidgetArrangementManager {
    fun store(arrangement: WidgetArrangement)
    fun load(): WidgetArrangement
}