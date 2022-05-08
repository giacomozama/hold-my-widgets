package me.zama.holdmywidgets.utils.permissions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class PermissionsStatusHelperImpl(
    private val context: Context
) : PermissionsStatusHelper {

    private val _hasLocationPermission = MutableStateFlow(hasLocationPermission())

    override val hasLocationPermission: Flow<Boolean> get() = _hasLocationPermission

    override fun hasLocationPermission() =
        context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED


    override fun refresh() {
        _hasLocationPermission.value = hasLocationPermission()
    }
}