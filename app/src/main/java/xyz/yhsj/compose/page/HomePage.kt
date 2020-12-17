package xyz.yhsj.compose.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import xyz.yhsj.compose.routes.Routes
import xyz.yhsj.compose.routes.Navigator

@Composable
fun HomePage() {
    val item =
        arrayListOf(
            "text",
            "button",
            "Image",
            "ListView",
            "GridView",
            "Dialog",
            "ViewPager",
            "Other",
            "Movie",
            "测试"
        )
    LazyColumn {
        items(items = item, itemContent = { data ->
            Column {
                TextButton(onClick = {
                    when (data) {
                        "text" -> Navigator.push(Routes.TEXT)
                        "button" -> Navigator.push(Routes.BUTTON)
                        "Image" -> Navigator.push(Routes.IMAGE)
                        "ListView" -> Navigator.push(Routes.LISTVIEW)
                        "GridView" -> Navigator.push(Routes.GRIDVIEW)
                        "Dialog" -> Navigator.push(Routes.DIALOG)
                        "ViewPager" -> Navigator.push(Routes.VIEWPAGER)
                        "Other" -> Navigator.push(Routes.OTHER)
                        "Movie" -> Navigator.push(Routes.MOVIE)
                        else -> Navigator.push("测试")
                    }


                }) {
                    Text(
                        fontSize = TextUnit.Sp(24),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillParentMaxWidth().padding(vertical = 16.dp),
                        text = data
                    )
                }
            }
            Divider()
        })
    }
}

