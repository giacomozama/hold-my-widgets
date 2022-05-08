package me.zama.holdmywidgets.mock.coords

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.zama.holdmywidgets.model.coords.CoordsLiveProvider
import me.zama.holdmywidgets.model.coords.CoordsLiveProviderModule

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [CoordsLiveProviderModule::class])
class CoordsLiveProviderTestModule {

    @Provides
    fun provideCoordsLiveProvider(): CoordsLiveProvider = CoordsLiveProviderTestImpl()
}