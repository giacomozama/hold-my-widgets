package me.zama.holdmywidgets.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.zama.holdmywidgets.model.widget.arrangement.WidgetArrangement

@Composable
fun WidgetList(
    modifier: Modifier = Modifier,
    widgetArrangement: WidgetArrangement,
    onRemoveWidget: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 128.dp)
    ) {
        itemsIndexed(
            items = widgetArrangement,
            key = { _, widget -> widget.uniqueId }
        ) { i, widget ->
            WidgetCard(
                modifier = Modifier.animateItemPlacement(),
                positionInList = i + 1,
                widget = widget,
                onRemove = {
                    onRemoveWidget(i)
                }
            )
        }
    }
}