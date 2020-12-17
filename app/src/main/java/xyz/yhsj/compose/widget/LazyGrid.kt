package xyz.yhsj.compose.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.WithConstraints

@Composable
fun <T> LazyVerticalGrid(
    items: List<T>,
    columns: Int = 2,
    itemContent: @Composable LazyItemScope.(item: T) -> Unit
) {
    val tmpColumns = if (columns < 2) 2 else columns
    val rows: List<List<T>> = items.withIndex()
        .groupBy { it.index / tmpColumns }
        .map { group -> group.value.map { indexedValue -> indexedValue.value } }
    LazyColumn {
        items(items = rows, itemContent = { item ->
            GridView(columns = tmpColumns, items = item) {
                itemContent(it)
            }
        })
    }
}

@Composable
private fun <T> GridView(
    columns: Int,
    items: List<T>,
    itemContent: @Composable (item: T) -> Unit
) {
    WithConstraints {
        Row {
            (0 until columns).forEach {
                Surface(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                ) {
                    if (it < items.size) {
                        itemContent(items[it])
                    }
                }
            }
        }
    }
}