package xyz.yhsj.compose.page

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import xyz.yhsj.compose.utils.Navigation

@Composable
fun TextPage() {

    val text = mutableStateOf("Text Page")
    val show = mutableStateOf(true)
    val delete = mutableStateOf(false)


    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Text") },
            elevation = 8.dp,
            navigationIcon = { IconButton(onClick = { Navigation.pop() }) { Icon(asset = Icons.Default.ArrowBack) } }
        )
    }) {

        Column() {

            Text(
                text = text.value,
                modifier = Modifier.clickable(onClick = {}).padding(8.dp).fillMaxWidth(),
                color = Color.Blue,
                fontSize = TextUnit.Sp(30),
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                letterSpacing = TextUnit.Companion.Sp(8),
                textDecoration = TextDecoration.LineThrough,
                textAlign = TextAlign.Right,
                //行高
                lineHeight = TextUnit.Sp(40),
                //超出后显示省略号啥的
                overflow = TextOverflow.Ellipsis,
//                    maxLines=5,
                onTextLayout = { println(it.size) },
                //预设字体样式
                style = typography.caption
            )
            TextField(
                value = text.value,
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                keyboardType = KeyboardType.Text,
                leadingIcon = { Icon(asset = Icons.Default.Person) },
                label = { Text(text = "用户名") },
                trailingIcon = {
                    if (delete.value)
                        IconButton(onClick = {
                            text.value = ""
                            delete.value = false
                        }) {
                            Icon(asset = Icons.Default.Close)
                        } else null
                },
                backgroundColor = Color.White,
                onTextInputStarted = { },
                placeholder = { Text(text = "请输入密码") },
                onValueChange = {
                    delete.value = it.isNotEmpty()
                    text.value = it
                })

            OutlinedTextField(
                value = text.value,
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                keyboardType = KeyboardType.Text,
                label = { Text(text = "Password") },
                placeholder = { Text(text = "请输入密码") },
                leadingIcon = { Icon(asset = Icons.Default.Lock) },
                isErrorValue = true,
                trailingIcon = {
                    IconButton(onClick = {
                        show.value = !show.value

                    }) {
                        Icon(asset = Icons.Default.Star)
                    }
                },
                visualTransformation = if (show.value) VisualTransformation.None else PasswordVisualTransformation(
                    '*'
                ),
                onValueChange = { text.value = it },
                imeAction = ImeAction.Search,
                onImeActionPerformed = { imeAction, softwareKeyboardController -> softwareKeyboardController?.hideSoftwareKeyboard() }
            )
        }
    }

}