package me.zama.holdmywidgets.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import me.zama.holdmywidgets.R
import me.zama.holdmywidgets.model.widget.WidgetDescription

@Composable
fun WidgetPickerDialog(
    onWidgetPicked: (WidgetDescription) -> Unit,
    onDismiss: () -> Unit
) {

    val options = WidgetDescription.values()

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .padding(start = 24.dp, end = 24.dp),
                        text = stringResource(id = R.string.add_widget),
                        style = MaterialTheme.typography.h3
                    )
                }
                for (option in options) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .clickable { onWidgetPicked(option) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
                            text = stringResource(id = option.title),
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }
}
