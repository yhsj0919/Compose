package xyz.yhsj.compose.page

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun topBar(title: String) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        elevation = 8.dp,
        actions = {
            IconButton(
                onClick = {},
                icon = { Icon(asset = Icons.Default.Search) }
            )

        }

    )
}