package xyz.yhsj.compose.page.movie

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage
import io.ktor.client.request.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.seimicrawler.xpath.JXDocument
import org.seimicrawler.xpath.JXNode
import xyz.yhsj.compose.utils.Navigation
import xyz.yhsj.compose.utils.httpClient

@Composable
fun MoviePage() {
    val (movies, setMovie) = remember { mutableStateOf(listOf<MovieEntity>()) }
    val (status, setStatus) = remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Movie") },
            elevation = 8.dp,
            navigationIcon = { IconButton(onClick = { Navigation.pop() }) { Icon(asset = Icons.Default.ArrowBack) } }
        )
    }) {
        Column {
            Button(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                onClick = {
                    setStatus(true)

                    getMovie(setMovie, setStatus)
                }) {
                Text(text = "获取列表")
            }

            if (movies.isEmpty() && status) {

                CircularProgressIndicator(
                    modifier = Modifier.width(60.dp).height(60.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )

            } else {
                LazyColumnFor(items = movies) { item ->
                    ListItem(
                        icon = {
                            CoilImage(
                                data = item.cover ?: "",
                                modifier = Modifier
//                                .preferredWidth(190.dp)
//                                .preferredHeight(300.dp)
//                                .padding(12.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                        },
                        text = { Text(text = item.title ?: "", maxLines = 1) },
                        secondaryText = { Text(text = item.des ?: "", maxLines = 1) }
                    )
                }
            }


        }
    }
}

fun getMovie(movieData: (List<MovieEntity>) -> Unit, status: (Boolean) -> Unit) {
    GlobalScope.launch {
        try {
            val html =
                httpClient.get<String>("https://www.douban.com/people/205055992/doulists/collect?start=0")

            val page: JXDocument = JXDocument.create(html)

            val thisPage = page.selOne("//span[@class='thispage']/text()")
            val totalPage = page.selOne("//span[@class='thispage']/@data-total-page")

            println(">>>>>thisPage>>>>>>>$thisPage")
            println(">>>>>totalPage>>>>>>>$totalPage")


            val movies: List<JXNode> = page.selN("//ul[@class='doulist-list']//li")
            val datas = movies.map {
                MovieEntity(
                    id = it.selOne("//span[@class='collect-stat']/a[1]/@data-id")?.asString(),
                    title = it.selOne("//h3[1]/a[1]/text()")?.asString(),
                    url = it.selOne("//h3[1]/a[1]/@href")?.asString(),
                    cover = it.selOne("//div[@class='doulist-cover']//img/@src")?.asString(),
                    des = it.selOne("//p[@class='intro']/text()")?.asString()
                )
            }
            movieData(datas)
        } catch (e: Exception) {
            status(false)
            e.printStackTrace()
        }
    }
}
