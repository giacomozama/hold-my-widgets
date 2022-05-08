package me.zama.holdmywidgets.model.coords

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import androidx.core.content.getSystemService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import me.zama.holdmywidgets.R
import me.zama.holdmywidgets.utils.permissions.PermissionsStatusHelper

class CoordsLiveProviderImpl(
    private val context: Context,
    private val permissionsStatusHelper: PermissionsStatusHelper
) : CoordsLiveProvider {

    override fun getLiveCoords() = permissionsStatusHelper.hasLocationPermission.flatMapLatest { hasPermission ->
        if (hasPermission) {
            try {
                return@flatMapLatest getLiveCoordsAssumingPermissions()
            } catch (_: SecurityException) {
                // ignored
            }
        }
        flowOf(Result.failure(Exception(context.getString(R.string.location_permissions_not_granted))))
    }

    @RequiresPermission(anyOf = [
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ])
    private fun getLiveCoordsAssumingPermissions(): Flow<Result<Location>> {
        val locationManager = context.getSystemService<LocationManager>()!!
        return callbackFlow {
            val callback = LocationListener {
                trySendBlocking(Result.success(it))
            }

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                0f,
                callback
            )

            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let {
                trySend(Result.success(it))
            }

            awaitClose {
                locationManager.removeUpdates(callback)
            }
        }
    }
}
