package me.zama.holdmywidgets.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import me.zama.holdmywidgets.R

@Composable
fun MainPage(viewModel: MainPageViewModel = viewModel()) {

    val widgetArrangement by viewModel.widgetArrangement.collectAsState()
    var isWidgetPickerOpen by rememberSaveable { mutableStateOf(false) }

    if (isWidgetPickerOpen) {
        WidgetPickerDialog(
            onWidgetPicked = {
                viewModel.addWidget(it)
                isWidgetPickerOpen = false
            },
            onDismiss = {
                isWidgetPickerOpen = false
            }
        )
    }

    Scaffold(
        topBar = {
            Text(
                modifier = Modifier.padding(24.dp),
                text = stringResource(id = R.string.my_dashboard),
                style = MaterialTheme.typography.h2
            )
        },
        floatingActionButton = {
            Button(
                modifier = Modifier.height(43.dp),
                onClick = { isWidgetPickerOpen = true }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = stringResource(id = R.string.add_widget)
                )
            }
        }
    ) {
        WidgetList(
            modifier = Modifier.padding(it),
            widgetArrangement = widgetArrangement,
            onRemoveWidget = viewModel::removeWidget
        )
    }
}