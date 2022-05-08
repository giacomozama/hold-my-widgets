package me.zama.holdmywidgets.mock.arrangement

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.zama.holdmywidgets.model.widget.arrangement.WidgetArrangementManager
import me.zama.holdmywidgets.model.widget.arrangement.WidgetArrangementManagerModule

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [WidgetArrangementManagerModule::class])
class WidgetArrangementManagerTestModule {

    @Provides
    fun provideWidgetArrangementManager(): WidgetArrangementManager = WidgetArrangementManagerTestImpl()
}