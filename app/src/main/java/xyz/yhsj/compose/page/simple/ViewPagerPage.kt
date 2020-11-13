package xyz.yhsj.compose.page.simple

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import xyz.yhsj.compose.routes.Navigator
import xyz.yhsj.compose.widget.ViewPager

@Composable
fun ViewPagerPage() {
    val mCurrent = remember { mutableStateOf(0) }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "ViewPager") },
            elevation = 8.dp,
            navigationIcon = { IconButton(onClick = { Navigator.pop() }) { Icon(asset = Icons.Default.ArrowBack) } }
        )
    }) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        ) {
            Button(onClick = {
                val random = (Math.random() * 5).toInt()

                mCurrent.value = random
            }) {
                Text(text = "随机切换")
            }
            Surface(modifier = Modifier) {
                ViewPager(
                    items = arrayListOf("item1", "item2", "item3", "item4", "item5"),
                    current = mCurrent.value,
                    modifier = Modifier.fillMaxSize().padding(horizontal = 80.dp),
                    onPageChanged = { Log.e("onPageChanged", "$it") }
                ) { _, item ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(8.dp).background(Color.Yellow),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = item)
                    }

                }
            }

        }


    }
}