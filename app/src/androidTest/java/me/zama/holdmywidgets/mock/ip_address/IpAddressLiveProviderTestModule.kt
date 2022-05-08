package me.zama.holdmywidgets.mock.ip_address

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.zama.holdmywidgets.model.ip_address.IpAddressLiveProvider
import me.zama.holdmywidgets.model.ip_address.IpAddressLiveProviderModule

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [IpAddressLiveProviderModule::class])
class IpAddressLiveProviderTestModule {

    @Provides
    fun provideIpAddressLiveProvider(): IpAddressLiveProvider = IpAddressLiveProviderTestImpl()
}