package me.zama.holdmywidgets.model.widget.arrangement

import android.content.Context
import androidx.core.content.edit
import me.zama.holdmywidgets.R
import me.zama.holdmywidgets.model.widget.Widget

class WidgetArrangementManagerImpl(
    private val context: Context,
    private val widgetFactory: Widget.Factory
) : WidgetArrangementManager {

    override fun store(arrangement: WidgetArrangement) {
        context.getSharedPreferences(context.getString(R.string.prefs_name), Context.MODE_PRIVATE).edit {
            putString(context.getString(R.string.prefs_key_widget_configuration), arrangement.serialize())
        }
    }

    override fun load(): WidgetArrangement {
        val serialized = context
            .getSharedPreferences(context.getString(R.string.prefs_name), Context.MODE_PRIVATE)
            .getString(context.getString(R.string.prefs_key_widget_configuration), null)
            ?.takeIf { it.isNotEmpty() }
            ?: return WidgetArrangement.Empty
        return WidgetArrangement(serialized.split(",").map { widgetFactory.create(it.toInt()) })
    }
}