package xyz.yhsj.compose.page.simple

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import xyz.yhsj.compose.routes.Navigator

@Composable
fun ButtonPage() {
    val checked = mutableStateOf(true)
    val radioChecked = mutableStateOf(0)


    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Button") },
            elevation = 8.dp,
            navigationIcon = { IconButton(onClick = { Navigator.pop() }) { Icon(imageVector = Icons.Default.ArrowBack) } }
        )
    }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp).fillMaxWidth()
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Text",
                    modifier = Modifier.clickable(onClick = {}).padding(8.dp)
                )
                TextButton(
                    onClick = {},
                    modifier = Modifier.padding(8.dp),
                    enabled = true,
                ) {
                    Text(text = "TextButton")
                }
                Button(
                    onClick = {},
                    modifier = Modifier.padding(8.dp),
                    enabled = true,
                ) {
                    Text(text = "Button")
                }
                Button(
                    onClick = {},
                    modifier = Modifier.padding(8.dp),
                    enabled = false,
                ) {
                    Text(text = "Button")
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = {},
                    modifier = Modifier.padding(8.dp),
                    enabled = true,
                    elevation = ButtonDefaults.elevation(defaultElevation = 18.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color.Red)
                ) {
                    Text(text = "Button")
                }

                OutlinedButton(onClick = {}) {
                    Text(text = "Outlined")
                }
                IconButton(onClick = {}) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = Icons.Default.Phone)
                        Text(modifier = Modifier.padding(start = 8.dp), text = "电话")
                    }
                }

                FloatingActionButton(onClick = {}) {
                    Row(Modifier.padding(start = 16.dp, end = 16.dp)) {
                        Icon(imageVector = Icons.Default.Phone)
                        Text(modifier = Modifier.padding(start = 8.dp), text = "电话")

                    }
                }
            }
            ExtendedFloatingActionButton(
                modifier = Modifier.fillMaxWidth(),
                text = {
                    Text(text = "电话")
                },
                onClick = {},
                icon = { Icon(imageVector = Icons.Default.Phone) }
            )
            Row(verticalAlignment = Alignment.CenterVertically) {


                IconToggleButton(
                    checked = checked.value,
                    onCheckedChange = {
                        checked.value = it
                    }
                ) {
                    if (checked.value) {
                        Icon(imageVector = Icons.Filled.Favorite)
                    } else {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder
                        )
                    }
                }
                Button(onClick = {}, modifier = Modifier.padding(8.dp)) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(text = "Button")
                    }
                }

                Checkbox(checked = checked.value, onCheckedChange = { checked.value = it })
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(checked = checked.value, onCheckedChange = { checked.value = it })

                Row(verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = { radioChecked.value = 0 }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = radioChecked.value == 0,
                                onClick = { radioChecked.value = 0 }
                            )
                            Text(text = "吃饭")
                        }
                    }

                    TextButton(onClick = { radioChecked.value = 1 }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = radioChecked.value == 1,
                                onClick = { radioChecked.value = 1 }
                            )
                            Text(text = "睡觉")
                        }
                    }

                    TextButton(onClick = { radioChecked.value = 2 }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = radioChecked.value == 2,
                                onClick = { radioChecked.value = 2 }
                            )
                            Text(text = "打豆豆")
                        }
                    }
                }
            }
        }
    }
}
