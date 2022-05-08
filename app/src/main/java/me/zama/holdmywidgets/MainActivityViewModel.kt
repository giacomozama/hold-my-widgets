package me.zama.holdmywidgets

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.zama.holdmywidgets.utils.permissions.PermissionsStatusHelper
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val permissionsStatusHelper: PermissionsStatusHelper
) : ViewModel() {

    fun onPermissionsStatusChanged() = permissionsStatusHelper.refresh()

    fun hasLocationPermission() = permissionsStatusHelper.hasLocationPermission()
}