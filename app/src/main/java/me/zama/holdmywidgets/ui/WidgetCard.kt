package me.zama.holdmywidgets.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.zama.holdmywidgets.R
import me.zama.holdmywidgets.model.widget.Widget

@Composable
fun WidgetCard(
    modifier: Modifier = Modifier,
    positionInList: Int,
    widget: Widget,
    onRemove: () -> Unit
) {

    val content by widget.getContents(LocalContext.current)
        .collectAsState(initial = listOf(stringResource(id = R.string.waiting_for_data)))

    Card(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "#$positionInList - ${stringResource(id = widget.title)}",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { onRemove() }
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.ic_remove),
                        tint = MaterialTheme.colors.onSurface,
                        contentDescription = stringResource(id = R.string.remove_this_widget)
                    )
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = content.joinToString("\n"),
                style = MaterialTheme.typography.body1
            )
        }
    }
}