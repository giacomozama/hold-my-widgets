package me.zama.holdmywidgets.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import me.zama.holdmywidgets.model.widget.Widget
import me.zama.holdmywidgets.model.widget.WidgetDescription
import me.zama.holdmywidgets.model.widget.arrangement.WidgetArrangementManager
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val widgetArrangementManager: WidgetArrangementManager,
    private val widgetFactory: Widget.Factory
) : ViewModel() {

    val widgetArrangement = MutableStateFlow(widgetArrangementManager.load())

    fun addWidget(widgetDescription: WidgetDescription) {
        val currentWidgetArrangement = widgetArrangement.value
        val newWidgetArrangement = currentWidgetArrangement + widgetFactory.create(widgetDescription)
        widgetArrangement.value = newWidgetArrangement
        widgetArrangementManager.store(newWidgetArrangement)
    }

    fun removeWidget(position: Int) {
        val currentWidgetArrangement = widgetArrangement.value
        val newWidgetArrangement = currentWidgetArrangement.without(position)
        widgetArrangement.value = newWidgetArrangement
        widgetArrangementManager.store(newWidgetArrangement)
    }
}