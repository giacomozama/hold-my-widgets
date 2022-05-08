package me.zama.holdmywidgets.model.widget

import android.content.Context
import android.os.Build
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.zama.holdmywidgets.R
import me.zama.holdmywidgets.model.time.TimeLiveProvider
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ClockWidget(private val timeLiveProvider: TimeLiveProvider) : Widget(WidgetDescription.Clock) {

    override fun getContents(context: Context): Flow<List<String>> {
        val locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0]
        } else {
            @Suppress("DEPRECATION")
            context.resources.configuration.locale
        }

        val dateFormatter = DateTimeFormatter.ofPattern("d E", locale)
        val timeFormatter = DateTimeFormatter.ofPattern("H:m", locale)

        return timeLiveProvider.getLiveTime().map {
            listOf(
                context.getString(R.string.time_template, timeFormatter.format(it)),
                context.getString(R.string.date_template, dateFormatter.format(it))
            )
        }
    }

    class Factory @Inject constructor(
        private val timeLiveProvider: TimeLiveProvider
    ) : WidgetFactory<ClockWidget> {

        override fun create() = ClockWidget(timeLiveProvider)
    }
}