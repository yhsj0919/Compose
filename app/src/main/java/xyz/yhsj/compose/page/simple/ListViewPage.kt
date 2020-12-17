package xyz.yhsj.compose.page.simple

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.yhsj.compose.R
import xyz.yhsj.compose.routes.Navigator

@Composable
fun ListViewPage() {
    val items = mutableStateOf(listOf<Int>())

    GlobalScope.launch {
        delay(250)
        items.value = (0..100).toList()
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "ListView") },
            elevation = 8.dp,
            navigationIcon = { IconButton(onClick = { Navigator.pop() }) { Icon(imageVector = Icons.Default.ArrowBack) } }
        )
    }) {

        LazyColumn {
            items(items = items.value, itemContent = {
                ListItem(
                    icon = {
                        Image(
                            bitmap = imageResource(R.drawable.image_1),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .preferredWidth(50.dp)
                                .preferredHeight(50.dp)
                                .clip(CircleShape)
                        )
                    },
                    secondaryText = { Text(text = "secondaryText") },
                    //overlineText = { Text(text = "overlineText") },
                    trailing = { Text(text = "trailing") },
                    singleLineSecondaryText = true,

                    ) {
                    Text(text = "Item$it")
                }
            })
        }


    }

}