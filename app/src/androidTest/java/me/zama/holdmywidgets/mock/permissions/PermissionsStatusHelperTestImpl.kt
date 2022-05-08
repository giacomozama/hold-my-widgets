package me.zama.holdmywidgets.mock.permissions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.zama.holdmywidgets.utils.permissions.PermissionsStatusHelper

class PermissionsStatusHelperTestImpl : PermissionsStatusHelper {

    override val hasLocationPermission: Flow<Boolean> = flowOf(true)

    override fun hasLocationPermission() = true

    override fun refresh() {
    }
}