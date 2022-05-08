package me.zama.holdmywidgets.mock.ip_address

import kotlinx.coroutines.flow.flowOf
import me.zama.holdmywidgets.model.ip_address.IpAddressLiveProvider

class IpAddressLiveProviderTestImpl : IpAddressLiveProvider {

    override fun getLiveIpAddress() = flowOf("123.456.78.90")
}