package me.zama.holdmywidgets.model.widget

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.zama.holdmywidgets.R
import me.zama.holdmywidgets.model.ip_address.IpAddressLiveProvider
import javax.inject.Inject

class IpAddressWidget(private val ipAddressLiveProvider: IpAddressLiveProvider) : Widget(WidgetDescription.IpAddress) {

    override fun getContents(context: Context): Flow<List<String>> =
        ipAddressLiveProvider.getLiveIpAddress().map {
            listOf(it ?: context.getString(R.string.not_available))
        }

    class Factory @Inject constructor(
        private val ipAddressLiveProvider: IpAddressLiveProvider
    ) : WidgetFactory<IpAddressWidget> {

        override fun create() = IpAddressWidget(ipAddressLiveProvider)
    }
}