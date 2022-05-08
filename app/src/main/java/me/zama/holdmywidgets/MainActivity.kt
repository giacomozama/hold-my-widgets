package me.zama.holdmywidgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.zama.holdmywidgets.ui.MainPage
import me.zama.holdmywidgets.ui.theme.HoldMyWidgetsTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    private val permissionRequestLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        viewModel.onPermissionsStatusChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HoldMyWidgetsTheme {
                MainPage()
            }
        }
        if (!viewModel.hasLocationPermission()) {
            permissionRequestLauncher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }
}
