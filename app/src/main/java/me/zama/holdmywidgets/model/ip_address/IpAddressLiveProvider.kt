package me.zama.holdmywidgets.model.ip_address

import kotlinx.coroutines.flow.Flow

interface IpAddressLiveProvider {
    fun getLiveIpAddress(): Flow<String?>
}