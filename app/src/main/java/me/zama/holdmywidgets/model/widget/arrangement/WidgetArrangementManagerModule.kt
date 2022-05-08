package me.zama.holdmywidgets.model.widget.arrangement

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.zama.holdmywidgets.model.widget.Widget

@Module
@InstallIn(SingletonComponent::class)
class WidgetArrangementManagerModule {

    @Provides
    fun provideWidgetArrangementManager(
        @ApplicationContext context: Context,
        widgetFactory: Widget.Factory
    ): WidgetArrangementManager = WidgetArrangementManagerImpl(context, widgetFactory)
}