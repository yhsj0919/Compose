package xyz.yhsj.compose.routes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun defView() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "404") },
                navigationIcon = { IconButton(onClick = { Navigator.pop() }) { Icon(imageVector = Icons.Default.ArrowBack) } }
            )
        }
    ) {
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Default Page", style = MaterialTheme.typography.h4)
        }
    }
}