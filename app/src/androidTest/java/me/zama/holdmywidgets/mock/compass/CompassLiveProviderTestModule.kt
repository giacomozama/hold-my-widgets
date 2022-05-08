package me.zama.holdmywidgets.mock.compass

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.zama.holdmywidgets.model.compass.CompassLiveProvider
import me.zama.holdmywidgets.model.compass.CompassLiveProviderModule

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [CompassLiveProviderModule::class])
class CompassLiveProviderTestModule {

    @Provides
    fun provideCompassLiveProvider(): CompassLiveProvider = CompassLiveProviderTestImpl()
}