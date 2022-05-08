package me.zama.holdmywidgets.model.ip_address

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.core.content.getSystemService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import me.zama.holdmywidgets.utils.okhttp.await
import okhttp3.OkHttpClient
import okhttp3.Request


class IpAddressLiveProviderImpl(
    private val context: Context,
    private val okHttpClient: OkHttpClient
) : IpAddressLiveProvider {

    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun fetchIpAddress(): String? {
        val request = Request.Builder().url("https://api.ipify.org/").build()
        return try {
            okHttpClient.newCall(request).await().use { it.body?.string() }
        } catch (_: Throwable) {
            null
        }
    }

    override fun getLiveIpAddress(): Flow<String?> {
        val connectivityManager = context.getSystemService<ConnectivityManager>()!!
        return callbackFlow {
            val callback = object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    launch {
                        trySendBlocking(fetchIpAddress())
                    }
                }

                override fun onLost(network: Network) {
                    launch {
                        trySendBlocking(null)
                    }
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager.registerDefaultNetworkCallback(callback)
            } else {
                val networkRequest = NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .build()
                connectivityManager.registerNetworkCallback(networkRequest, callback)
            }

            trySendBlocking(fetchIpAddress())

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }
    }
}