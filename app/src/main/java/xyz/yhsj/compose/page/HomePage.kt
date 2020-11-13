package xyz.yhsj.compose.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import xyz.yhsj.compose.page.movie.MoviePage
import xyz.yhsj.compose.page.simple.*
import xyz.yhsj.compose.utils.Navigation

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
    LazyColumnFor(items = item) { item ->
        Column {
            TextButton(onClick = {
                when (item) {
                    "text" -> Navigation.push { TextPage() }
                    "button" -> Navigation.push { ButtonPage() }
                    "Image" -> Navigation.push { ImagePage() }
                    "ListView" -> Navigation.push { ListViewPage() }
                    "GridView" -> Navigation.push { GridViewPage() }
                    "Dialog" -> Navigation.push { DialogPage() }
                    "ViewPager" -> Navigation.push { ViewPagerPage() }
                    "Other" -> Navigation.push { OtherWidgetPage() }
                    "Movie" -> Navigation.push { MoviePage() }
                }


            }) {
                Text(
                    fontSize = TextUnit.Sp(24),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillParentMaxWidth().padding(vertical = 16.dp),
                    text = item
                )
            }
        }
        Divider()
    }
}

