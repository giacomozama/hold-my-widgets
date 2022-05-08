package me.zama.holdmywidgets.model.widget

import android.content.Context
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

abstract class Widget(description: WidgetDescription) {

    val id = description.id
    val title = description.title

    val uniqueId: UUID = UUID.randomUUID()

    abstract fun getContents(context: Context): Flow<List<String>>

    interface WidgetFactory<out T : Widget> {

        fun create(): T
    }

    class Factory @Inject constructor(
        clockWidgetFactory: ClockWidget.Factory,
        compassWidgetFactory: CompassWidget.Factory,
        coordsWidgetFactory: CoordsWidget.Factory,
        ipAddressWidgetFactory: IpAddressWidget.Factory
    ) {
        private val factories = mapOf(
            WidgetDescription.Clock to clockWidgetFactory,
            WidgetDescription.Compass to compassWidgetFactory,
            WidgetDescription.Coords to coordsWidgetFactory,
            WidgetDescription.IpAddress to ipAddressWidgetFactory
        )

        @Suppress("UNCHECKED_CAST")
        fun create(description: WidgetDescription): Widget {
            val factory = factories.getOrElse(description) {
                throw IllegalArgumentException("Can't create $description")
            }
            return factory.create()
        }

        @Suppress("UNCHECKED_CAST")
        fun create(id: Int): Widget = factories.getOrElse(WidgetDescription.getById(id)) {
            throw IllegalArgumentException("Invalid ID $id")
        }.create()
    }
}
