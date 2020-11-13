package xyz.yhsj.compose.page.simple

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xyz.yhsj.compose.routes.Navigator
import xyz.yhsj.compose.widget.LazyVerticalGrid

@Composable
fun GridViewPage() {
    val items = mutableStateOf(listOf<Int>())

    val (columns, setColumns) = remember { mutableStateOf(2) }

    GlobalScope.launch {
        delay(250)
        items.value = (0..100).toList()
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "GridView") },
            elevation = 8.dp,
            navigationIcon = { IconButton(onClick = { Navigator.pop() }) { Icon(asset = Icons.Default.ArrowBack) } }
        )
    }) {

        Crossfade(current = columns) {
            LazyVerticalGrid(
                items = items.value,
                columns = columns
            ) {
                Box {
                    CoilImage(
                        data = "https://img1.doubanio.com/dae/frodo/img_handler/doulist_cover/3901543/round_rec",
                        modifier = Modifier.wrapContentWidth()
                            .height(120.dp).align(Alignment.Center)
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        text = "item$it",
                        color = Color.White,
                        modifier = Modifier.background(Color.Black)
                    )
                }

            }
        }
        Button(onClick = {
            if (columns == 2) {
                setColumns(3)
            } else {
                setColumns(2)
            }
        }) {
            Text(text = "修改列数")

        }

//        LazyColumnFor(items = items.value) {
//            Row(modifier = Modifier.fillParentMaxWidth()) {
//
//                CoilImage(
//                    data = "https://img1.doubanio.com/dae/frodo/img_handler/doulist_cover/3901543/round_rec",
//                    modifier = Modifier.fillParentMaxWidth()
//                        .height(120.dp)
//                        .weight(1f, fill = true)
//                        .padding(vertical = 8.dp)
//                        .clip(RoundedCornerShape(12.dp)),
//                    contentScale = ContentScale.Fit
//                )
//                CoilImage(
//                    data = "https://img1.doubanio.com/dae/frodo/img_handler/doulist_cover/3901543/round_rec",
//                    modifier = Modifier.fillParentMaxWidth()
//                        .height(120.dp)
//                        .weight(1f, fill = true)
//                        .padding(vertical = 8.dp)
//                        .clip(RoundedCornerShape(12.dp)),
//                    contentScale = ContentScale.Fit
//                )
//            }
//
//
//        }

    }

}