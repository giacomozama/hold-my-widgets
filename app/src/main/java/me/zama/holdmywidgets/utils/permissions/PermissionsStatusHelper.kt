package me.zama.holdmywidgets.utils.permissions

import kotlinx.coroutines.flow.Flow

interface PermissionsStatusHelper {
    val hasLocationPermission: Flow<Boolean>
    fun hasLocationPermission(): Boolean
    fun refresh()
}