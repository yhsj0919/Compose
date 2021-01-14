package xyz.yhsj.compose.page.simple

import android.util.Log
import androidx.annotation.Px
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import xyz.yhsj.compose.R
import xyz.yhsj.compose.routes.Navigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OtherWidgetPage() {

    val sliderValue = remember { mutableStateOf(0f) }
    val bottomDrawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val expanded = remember { mutableStateOf(false) }

    val width = 350.dp
    val squareSize = 50.dp
    val swipeableState = rememberSwipeableState("A")
    val sizePx = with(AmbientDensity.current) { (width - squareSize).toPx() }
    val anchors = mapOf(0f to "A", sizePx / 2 to "B", sizePx to "C")

    //底部抽屉
    BottomDrawerLayout(
        gesturesEnabled = true,
        drawerState = bottomDrawerState,
        drawerContent = {
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp),
                onClick = { bottomDrawerState.close() },
                content = { Text("Close Drawer") }
            )
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            drawerBackgroundColor = Color.Gray,
            drawerContent = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 16.dp),
                        onClick = { scaffoldState.drawerState.close() },
                        content = { Text("关闭侧边栏") }
                    )
                }

            },
            //自定义一个SnackBar
            snackbarHost = {
                SnackbarHost(it) { data ->
                    Snackbar(
                        modifier = Modifier.padding(8.dp),
                        action = {
                            if (!data.actionLabel.isNullOrEmpty()) {
                                TextButton(onClick = { data.performAction() }) {
                                    Text(text = data.actionLabel!!)
                                }
                            }
                        },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Info)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = data.message)
                            }
                        }
                    )
                }
            },
            topBar = {
                TopAppBar(
                    title = { Text(text = "OtherWidget") },
                    elevation = 8.dp,
                    navigationIcon = { IconButton(onClick = { Navigator.pop() }) { Icon(imageVector = Icons.Default.ArrowBack) } },
                    actions = {


                    }
                )
            }) {

            ScrollableColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    elevation = 4.dp
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            bitmap = imageResource(id = R.drawable.image_2),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.preferredSize(60.dp)
                        )
                        Text(text = "这是一个Card 这里显示一些文字啥的", modifier = Modifier.padding(16.dp))
                    }
                }
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator()
                    Spacer(modifier = Modifier.width(8.dp))
                    CircularProgressIndicator()
                }
                LinearProgressIndicator(progress = sliderValue.value / 100)

                Slider(
                    value = sliderValue.value,
                    valueRange = 0f..100f,
                    onValueChange = {
                        sliderValue.value = it
                    })

                Slider(
                    valueRange = 0f..100f,
                    steps = 10,
                    value = sliderValue.value,
                    onValueChange = {
                        sliderValue.value = it
                    })
//
                Button(onClick = {
                    //这里获取Snackbar的一些操作,SnackBar只能在协程里操作
                    scope.launch {
                        val result = scaffoldState.snackbarHostState.showSnackbar(
                            "Snackbar",
                            actionLabel = "确定"
                        )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                Log.e("Snackbar", ">>>ActionPerformed>>>>>")
                            }
                            SnackbarResult.Dismissed -> {
                                Log.e("Snackbar", ">>>Dismissed>>>>>")
                            }
                        }
                    }
                }) {
                    Text(text = "显示Snackbar")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    scaffoldState.drawerState.open()
                }) {
                    Text(text = "显示侧边栏")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    bottomDrawerState.open()
                }) {
                    Text(text = "显示底部抽屉")
                }

                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 8.dp,
                            shape = CutCornerShape(16.dp),
                            clip = true
                        )
                        .background(color = MaterialTheme.colors.primary)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "随便显示点什么东西")
                }

                //官方代码搞过来的,控件强调等级
                //https://github.com/androidx/androidx/blob/1915e4a034a9778fdd0819a6a16aea0a5b1adedb/compose/material/material/samples/src/main/java/androidx/compose/material/samples
                Column {
                    Text("No emphasis applied - 100% opacity")
                    Providers(AmbientContentAlpha provides ContentAlpha.high) {
                        Text("High emphasis applied - 87% opacity")
                    }
                    Providers(AmbientContentAlpha provides ContentAlpha.medium) {
                        Text("Medium emphasis applied - 60% opacity")
                    }
                    Providers(AmbientContentAlpha provides ContentAlpha.disabled) {
                        Text("Disabled emphasis applied - 38% opacity")
                    }
                }

                val iconButton = @Composable {

                    Button(onClick = { expanded.value = true }) {
                        Text(text = "打开一个菜单")
                    }

                }
//
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                    toggle = iconButton,
                    //这个决定了按钮的位置
                    toggleModifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.TopCenter),

                    ) {
                    DropdownMenuItem(onClick = { expanded.value = false }) {
                        Text("Refresh")
                    }
                    DropdownMenuItem(onClick = { expanded.value = false }) {
                        Text("Settings")
                    }
                    Divider()
                    DropdownMenuItem(onClick = { expanded.value = false }) {
                        Text("Send Feedback")
                    }
                }

//                Box(
//                    modifier = Modifier
//                        .preferredWidth(width)
//                        .swipeable(
//                            state = swipeableState,
//                            anchors = anchors,
//                            thresholds = { _, _ -> FractionalThreshold(0.5f) },
//                            orientation = Orientation.Horizontal
//                        )
//                        .background(color = Color.Black),
//                ) {
//                    Box(
//                        Modifier
//                            .offset(x = if (!swipeableState.offset.value.isNaN()) swipeableState.offset.value.dp else 0.dp)
//                            .preferredSize(squareSize)
//                            .background(color = Color.Red),
//                        contentAlignment = Alignment.Center,
//                    ) {
//                        Text(text = swipeableState.value, color = Color.White, fontSize = 24.sp)
//                    }
//                }

            }
        }
    }
}

